package com.example.sigmacapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rutas")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Long idRuta;

    @Column(name = "id_grafo")
    private Integer idGrafo;

    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;

    @Column(name = "distancia")
    private Double distancia;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "tiempo")
    private Double tiempo;

    @Column(name = "fecha_generacion")
    private LocalDateTime fechaGeneracion;

    public Long getIdRuta() { return idRuta; }
    public Integer getIdGrafo() { return idGrafo; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public Double getDistancia() { return distancia; }
    public Double getCosto() { return costo; }
    public Double getTiempo() { return tiempo; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
}
