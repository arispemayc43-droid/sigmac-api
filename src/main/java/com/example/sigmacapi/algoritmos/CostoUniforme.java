package com.example.sigmacapi.algoritmos;

import java.util.*;

/**
 * Implementación del algoritmo de Costo Uniforme (Dijkstra).
 * Encuentra el camino de menor costo usando una cola de prioridad.
 * Complejidad temporal: O((V + E) log V)
 * Complejidad espacial: O(V)
 *
 * @author Constructora Gonzales Garcia S.R.L.
 * @version 1.0
 */
public class CostoUniforme {

    /**
     * Calcula la ruta de menor costo entre dos nodos.
     *
     * @param grafo     Grafo ponderado sobre el que se realiza la búsqueda
     * @param origenId  Identificador del nodo origen
     * @param destinoId Identificador del nodo destino
     * @return Lista de IDs de nodos que forman la ruta óptima
     */
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

    /**
     * Reconstruye la ruta óptima desde el destino al origen.
     *
     * @param padre   Mapa de nodo -> nodo padre
     * @param origen  Identificador del nodo origen
     * @param destino Identificador del nodo destino
     * @return Lista ordenada de IDs desde origen hasta destino
     */
    private static List<String> reconstruirRuta(Map<String, String> padre,
                                                String origen, String destino) {
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