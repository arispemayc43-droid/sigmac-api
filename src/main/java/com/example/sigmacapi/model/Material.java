package com.example.sigmacapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "materiales")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Long idMaterial;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "descripcion")
    private String descripcion;

    public Long getIdMaterial() { return idMaterial; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getUnidadMedida() { return unidadMedida; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public String getDescripcion() { return descripcion; }
}