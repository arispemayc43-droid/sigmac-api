package com.example.sigmacapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rol")
    private String rol;

    @Column(name = "iniciales")
    private String iniciales;

    @Column(name = "color")
    private String color;

    @Column(name = "estado")
    private String estado;

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
    public String getIniciales() { return iniciales; }
    public String getColor() { return color; }
    public String getEstado() { return estado; }

    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setRol(String rol) { this.rol = rol; }
    public void setIniciales(String iniciales) { this.iniciales = iniciales; }
    public void setColor(String color) { this.color = color; }
    public void setEstado(String estado) { this.estado = estado; }
}