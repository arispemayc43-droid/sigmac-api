package com.example.sigmacapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long idMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "costo_total")
    private Double costoTotal;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "id_usuario")
    private String idUsuario;

    public Long getIdMovimiento() { return idMovimiento; }
    public Material getMaterial() { return material; }
    public Obra getObra() { return obra; }
    public String getTipo() { return tipo; }
    public Double getCantidad() { return cantidad; }
    public Double getCostoTotal() { return costoTotal; }
    public LocalDateTime getFecha() { return fecha; }
    public String getMotivo() { return motivo; }
    public String getIdUsuario() { return idUsuario; }
}