package com.example.sigmacapi.algoritmos;

import java.util.*;

public class Greedy {

    public static List<String> calcular(Grafo grafo, String origenId, String destinoId) {
        Map<String, String> padre = new HashMap<>();
        Set<String> visitados = new HashSet<>();
        List<String> ruta = new ArrayList<>();

        String actual = origenId;
        visitados.add(actual);

        while (!actual.equals(destinoId)) {
            List<Arista> aristas = grafo.getAristas(actual);
            if (aristas.isEmpty()) break;

            Arista mejor = null;
            double menorCosto = Double.MAX_VALUE;

            for (Arista arista : aristas) {
                String vecinoId = arista.getDestino().getId();
                if (!visitados.contains(vecinoId) && arista.getCosto() < menorCosto) {
                    menorCosto = arista.getCosto();
                    mejor = arista;
                }
            }

            if (mejor == null) break;

            String siguiente = mejor.getDestino().getId();
            padre.put(siguiente, actual);
            visitados.add(siguiente);
            actual = siguiente;
        }

        // Reconstruir ruta
        String nodo = destinoId;
        while (nodo != null) {
            ruta.add(0, nodo);
            nodo = padre.get(nodo);
        }

        if (!ruta.isEmpty() && ruta.get(0).equals(origenId)) return ruta;
        return new ArrayList<>();
    }
}