package com.example.sigmacapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "obras")
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra")
    private Long idObra;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "responsable")
    private String responsable;

    @Column(name = "contratista")
    private String contratista;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "estado")
    private String estado;

    public Long getIdObra() { return idObra; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public String getResponsable() { return responsable; }
    public String getContratista() { return contratista; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getEstado() { return estado; }
}