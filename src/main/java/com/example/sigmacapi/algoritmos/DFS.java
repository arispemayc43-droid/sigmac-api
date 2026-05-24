package com.example.sigmacapi.algoritmos;

import java.util.*;

/**
 * Implementación del algoritmo DFS (Depth First Search).
 * Explora el grafo en profundidad usando una pila (Stack).
 * Complejidad temporal: O(V + E)
 * Complejidad espacial: O(V)
 *
 * @author Constructora Gonzales Garcia S.R.L.
 * @version 1.0
 */
public class DFS {

    /**
     * Calcula una ruta entre dos nodos usando búsqueda en profundidad.
     * Explora tan profundo como sea posible antes de retroceder.
     *
     * @param grafo     Grafo sobre el que se realiza la búsqueda
     * @param origenId  Identificador del nodo origen
     * @param destinoId Identificador del nodo destino
     * @return Lista de IDs de nodos que forman la ruta, vacía si no hay camino
     */
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

    /**
     * Reconstruye la ruta desde el destino hasta el origen usando el mapa de padres.
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