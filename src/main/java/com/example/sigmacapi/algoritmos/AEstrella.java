package com.example.sigmacapi.algoritmos;

import java.util.*;

public class AEstrella {

    public static List<String> calcular(Grafo grafo, String origenId, String destinoId) {
        Nodo destino = grafo.getNodo(destinoId);
        if (destino == null) return new ArrayList<>();

        PriorityQueue<double[]> cola = new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));
        Map<String, Double> gCosto = new HashMap<>();
        Map<String, String> padre = new HashMap<>();

        gCosto.put(origenId, 0.0);
        cola.add(new double[]{heuristica(grafo.getNodo(origenId), destino), origenId.hashCode()});

        while (!cola.isEmpty()) {
            double[] actual = cola.poll();
            String actualId = grafo.getNodos().keySet().stream()
                    .filter(k -> k.hashCode() == (int) actual[1])
                    .findFirst().orElse(null);

            if (actualId == null) continue;
            if (actualId.equals(destinoId)) break;

            for (Arista arista : grafo.getAristas(actualId)) {
                String vecinoId = arista.getDestino().getId();
                double nuevoG = gCosto.get(actualId) + arista.getCosto();

                if (!gCosto.containsKey(vecinoId) || nuevoG < gCosto.get(vecinoId)) {
                    gCosto.put(vecinoId, nuevoG);
                    padre.put(vecinoId, actualId);
                    double f = nuevoG + heuristica(arista.getDestino(), destino);
                    cola.add(new double[]{f, vecinoId.hashCode()});
                }
            }
        }

        return reconstruirRuta(padre, origenId, destinoId);
    }

    // Heurística: distancia en línea recta (Haversine)
    private static double heuristica(Nodo a, Nodo b) {
        if (a == null || b == null) return 0;
        final int R = 6371;
        double dLat = Math.toRadians(b.getLat() - a.getLat());
        double dLng = Math.toRadians(b.getLng() - a.getLng());
        double h = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat()))
                * Math.sin(dLng/2) * Math.sin(dLng/2);
        return R * 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1-h));
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