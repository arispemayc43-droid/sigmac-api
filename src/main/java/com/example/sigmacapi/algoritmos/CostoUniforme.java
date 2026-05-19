package com.example.sigmacapi.algoritmos;

import java.util.*;

public class CostoUniforme {

    public static List<String> calcular(Grafo grafo, String origenId, String destinoId) {
        PriorityQueue<double[]> cola = new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));
        Map<String, Double> costos = new HashMap<>();
        Map<String, String> padre = new HashMap<>();

        cola.add(new double[]{0, origenId.hashCode()});
        costos.put(origenId, 0.0);

        while (!cola.isEmpty()) {
            double[] actual = cola.poll();
            String actualId = grafo.getNodos().keySet().stream()
                    .filter(k -> k.hashCode() == (int) actual[1])
                    .findFirst().orElse(null);

            if (actualId == null) continue;
            if (actualId.equals(destinoId)) break;

            for (Arista arista : grafo.getAristas(actualId)) {
                String vecinoId = arista.getDestino().getId();
                double nuevoCosto = costos.get(actualId) + arista.getCosto();

                if (!costos.containsKey(vecinoId) || nuevoCosto < costos.get(vecinoId)) {
                    costos.put(vecinoId, nuevoCosto);
                    padre.put(vecinoId, actualId);
                    cola.add(new double[]{nuevoCosto, vecinoId.hashCode()});
                }
            }
        }

        return reconstruirRuta(padre, origenId, destinoId);
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