package com.example.sigmacapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long idInventario;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @Column(name = "cantidad_disponible")
    private Double cantidadDisponible;

    @Column(name = "stock_minimo")
    private Double stockMinimo;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Long getIdInventario() { return idInventario; }
    public Material getMaterial() { return material; }
    public Double getCantidadDisponible() { return cantidadDisponible; }
    public Double getStockMinimo() { return stockMinimo; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
}
