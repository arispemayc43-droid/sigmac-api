package com.example.sigmacapi.algoritmos;

import java.util.*;

/**
 * Implementación del algoritmo Greedy (Voraz).
 * En cada paso selecciona la arista de menor costo sin considerar el camino global.
 * Complejidad temporal: O(V²)
 * Complejidad espacial: O(V)
 *
 * @author Constructora Gonzales Garcia S.R.L.
 * @version 1.0
 */
public class Greedy {

    /**
     * Calcula una ruta usando el algoritmo voraz.
     * No garantiza la ruta óptima global, pero es más rápido.
     *
     * @param grafo     Grafo sobre el que se realiza la búsqueda
     * @param origenId  Identificador del nodo origen
     * @param destinoId Identificador del nodo destino
     * @return Lista de IDs de nodos que forman la ruta encontrada
     */
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

        String nodo = destinoId;
        while (nodo != null) {
            ruta.add(0, nodo);
            nodo = padre.get(nodo);
        }

        if (!ruta.isEmpty() && ruta.get(0).equals(origenId)) return ruta;
        return new ArrayList<>();
    }
}