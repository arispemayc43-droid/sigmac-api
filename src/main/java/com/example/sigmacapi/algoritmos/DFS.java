package com.example.sigmacapi.algoritmos;

import java.util.*;

public class DFS {

    public static List<String> calcular(Grafo grafo, String origenId, String destinoId) {
        Stack<String> pila = new Stack<>();
        Set<String> visitados = new HashSet<>();
        Map<String, String> padre = new HashMap<>();

        pila.push(origenId);
        visitados.add(origenId);
        padre.put(origenId, null);

        while (!pila.isEmpty()) {
            String actual = pila.pop();

            if (actual.equals(destinoId)) {
                return reconstruirRuta(padre, origenId, destinoId);
            }

            for (Arista arista : grafo.getAristas(actual)) {
                String vecinoId = arista.getDestino().getId();
                if (!visitados.contains(vecinoId)) {
                    visitados.add(vecinoId);
                    padre.put(vecinoId, actual);
                    pila.push(vecinoId);
                }
            }
        }
        return new ArrayList<>();
    }

    private static List<String> reconstruirRuta(Map<String, String> padre, String origen, String destino) {
        List<String> ruta = new ArrayList<>();
        String actual = destino;
        while (actual != null) {
            ruta.add(0, actual);
            actual = padre.get(actual);
        }
        if (!ruta.isEmpty() && ruta.get(0).equals(origen)) return ruta;
        return new ArrayList<>();
    }
}