package com.example.sigmacapi.algoritmos;

public class Arista {
    private Nodo origen;
    private Nodo destino;
    private double costo;

    public Arista(Nodo origen, Nodo destino, double costo) {
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
    }

    public Nodo getOrigen() { return origen; }
    public Nodo getDestino() { return destino; }
    public double getCosto() { return costo; }
}