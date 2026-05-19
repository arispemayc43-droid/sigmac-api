package com.example.sigmacapi.algoritmos;

public class Nodo {
    private String id;
    private String nombre;
    private double lat;
    private double lng;

    public Nodo(String id, String nombre, double lat, double lng) {
        this.id = id;
        this.nombre = nombre;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getLat() { return lat; }
    public double getLng() { return lng; }

    @Override
    public String toString() { return nombre; }
}