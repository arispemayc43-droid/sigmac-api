package com.example.sigmacapi.algoritmos;

import java.util.*;

public class Grafo {
    private Map<String, Nodo> nodos = new HashMap<>();
    private Map<String, List<Arista>> adyacencia = new HashMap<>();

    public void agregarNodo(Nodo nodo) {
        nodos.put(nodo.getId(), nodo);
        adyacencia.put(nodo.getId(), new ArrayList<>());
    }

    public void agregarArista(String origenId, String destinoId, double costo) {
        Nodo origen = nodos.get(origenId);
        Nodo destino = nodos.get(destinoId);
        if (origen != null && destino != null) {
            adyacencia.get(origenId).add(new Arista(origen, destino, costo));
        }
    }

    public Map<String, Nodo> getNodos() { return nodos; }
    public List<Arista> getAristas(String nodoId) {
        return adyacencia.getOrDefault(nodoId, new ArrayList<>());
    }
    public Nodo getNodo(String id) { return nodos.get(id); }
}