package com.example.sigmacapi.controller;

import com.example.sigmacapi.algoritmos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/rutas")
@CrossOrigin(origins = "*")
public class RutasController {

    private Grafo construirGrafo(double origenLat, double origenLng, double destinoLat, double destinoLng) {
        Grafo grafo = new Grafo();

        // Nodos origen
        Nodo o1 = new Nodo("O1", "Almacén Gonzales", -17.3932, -66.1568);
        Nodo o2 = new Nodo("O2", "Almacén García", -17.3780, -66.2050);

        // Nodos intermedios (intersecciones clave de Cochabamba)
        Nodo n1 = new Nodo("N1", "Plaza Principal", -17.3941, -66.1561);
        Nodo n2 = new Nodo("N2", "Av. América", -17.3860, -66.1520);
        Nodo n3 = new Nodo("N3", "Av. Heroínas", -17.3900, -66.1600);
        Nodo n4 = new Nodo("N4", "Av. Ballivián", -17.3820, -66.1650);

        // Nodo destino
        Nodo dest = new Nodo("DEST", "Destino", destinoLat, destinoLng);

        // Agregar nodos
        grafo.agregarNodo(o1);
        grafo.agregarNodo(o2);
        grafo.agregarNodo(n1);
        grafo.agregarNodo(n2);
        grafo.agregarNodo(n3);
        grafo.agregarNodo(n4);
        grafo.agregarNodo(dest);

        // Agregar aristas con costos (km)
        grafo.agregarArista("O1", "N1", 1.2);
        grafo.agregarArista("O1", "N3", 2.1);
        grafo.agregarArista("O2", "N4", 1.8);
        grafo.agregarArista("O2", "N3", 3.2);
        grafo.agregarArista("N1", "N2", 2.5);
        grafo.agregarArista("N1", "DEST", calcularDistancia(n1, dest));
        grafo.agregarArista("N2", "DEST", calcularDistancia(n2, dest));
        grafo.agregarArista("N3", "N2", 1.5);
        grafo.agregarArista("N3", "DEST", calcularDistancia(n3, dest));
        grafo.agregarArista("N4", "N2", 2.0);
        grafo.agregarArista("N4", "DEST", calcularDistancia(n4, dest));

        return grafo;
    }

    private double calcularDistancia(Nodo a, Nodo b) {
        final int R = 6371;
        double dLat = Math.toRadians(b.getLat() - a.getLat());
        double dLng = Math.toRadians(b.getLng() - a.getLng());
        double h = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat()))
                * Math.sin(dLng/2) * Math.sin(dLng/2);
        return R * 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1-h));
    }

    @PostMapping("/calcular")
    public ResponseEntity<?> calcularRuta(@RequestBody Map<String, Object> body) {
        String origenId = (String) body.get("origenId");
        double origenLat = ((Number) body.get("origenLat")).doubleValue();
        double origenLng = ((Number) body.get("origenLng")).doubleValue();
        double destinoLat = ((Number) body.get("destinoLat")).doubleValue();
        double destinoLng = ((Number) body.get("destinoLng")).doubleValue();

        Grafo grafo = construirGrafo(origenLat, origenLng, destinoLat, destinoLng);

        long t1 = System.nanoTime();
        List<String> rutaCU = CostoUniforme.calcular(grafo, origenId, "DEST");
        long tiempoCU = System.nanoTime() - t1;

        long t2 = System.nanoTime();
        List<String> rutaGreedy = Greedy.calcular(grafo, origenId, "DEST");
        long tiempoGreedy = System.nanoTime() - t2;

        long t3 = System.nanoTime();
        List<String> rutaA = AEstrella.calcular(grafo, origenId, "DEST");
        long tiempoA = System.nanoTime() - t3;

        Map<String, Object> result = new HashMap<>();
        result.put("costoUniforme", Map.of("ruta", rutaCU, "tiempo", tiempoCU));
        result.put("greedy", Map.of("ruta", rutaGreedy, "tiempo", tiempoGreedy));
        result.put("aEstrella", Map.of("ruta", rutaA, "tiempo", tiempoA));

        return ResponseEntity.ok(result);
    }
}