package com.example.sigmacapi.algoritmos;

import java.util.*;

/**
 * Representa un grafo dirigido y ponderado para el sistema de distribución.
 * Utiliza una lista de adyacencia para almacenar las conexiones entre nodos.
 *
 * @author Constructora Gonzales Garcia S.R.L.
 * @version 1.0
 */
public class Grafo {

    /** Mapa de nodos indexados por su identificador */
    private Map<String, Nodo> nodos = new HashMap<>();

    /** Lista de adyacencia: cada nodo tiene una lista de aristas salientes */
    private Map<String, List<Arista>> adyacencia = new HashMap<>();

    /**
     * Agrega un nodo al grafo.
     *
     * @param nodo Nodo a agregar
     */
    public void agregarNodo(Nodo nodo) {
        nodos.put(nodo.getId(), nodo);
        adyacencia.put(nodo.getId(), new ArrayList<>());
    }

    /**
     * Agrega una arista dirigida entre dos nodos con un costo dado.
     *
     * @param origenId  Identificador del nodo origen
     * @param destinoId Identificador del nodo destino
     * @param costo     Costo o distancia de la arista en km
     */
    public void agregarArista(String origenId, String destinoId, double costo) {
        Nodo origen = nodos.get(origenId);
        Nodo destino = nodos.get(destinoId);
        if (origen != null && destino != null) {
            adyacencia.get(origenId).add(new Arista(origen, destino, costo));
        }
    }

    /**
     * Retorna todos los nodos del grafo.
     *
     * @return Mapa de nodos indexados por identificador
     */
    public Map<String, Nodo> getNodos() { return nodos; }

    /**
     * Retorna las aristas salientes de un nodo.
     *
     * @param nodoId Identificador del nodo
     * @return Lista de aristas salientes
     */
    public List<Arista> getAristas(String nodoId) {
        return adyacencia.getOrDefault(nodoId, new ArrayList<>());
    }

    /**
     * Retorna un nodo por su identificador.
     *
     * @param id Identificador del nodo
     * @return Nodo correspondiente o null si no existe
     */
    public Nodo getNodo(String id) { return nodos.get(id); }
}