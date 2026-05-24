package com.example.sigmacapi.algoritmos;

/**
 * Representa un nodo (vértice) en el grafo de distribución.
 * Cada nodo tiene una ubicación geográfica definida por latitud y longitud.
 *
 * @author Constructora Gonzales Garcia S.R.L.
 * @version 1.0
 */
public class Nodo {

    /** Identificador único del nodo */
    private String id;

    /** Nombre descriptivo del nodo */
    private String nombre;

    /** Latitud geográfica del nodo */
    private double lat;

    /** Longitud geográfica del nodo */
    private double lng;

    /**
     * Constructor para crear un nuevo nodo.
     *
     * @param id     Identificador único del nodo
     * @param nombre Nombre descriptivo del nodo
     * @param lat    Latitud geográfica
     * @param lng    Longitud geográfica
     */
    public Nodo(String id, String nombre, double lat, double lng) {
        this.id = id;
        this.nombre = nombre;
        this.lat = lat;
        this.lng = lng;
    }

    /** @return Identificador único del nodo */
    public String getId() { return id; }

    /** @return Nombre descriptivo del nodo */
    public String getNombre() { return nombre; }

    /** @return Latitud geográfica del nodo */
    public double getLat() { return lat; }

    /** @return Longitud geográfica del nodo */
    public double getLng() { return lng; }

    @Override
    public String toString() { return nombre; }
}