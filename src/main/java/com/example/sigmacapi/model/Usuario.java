package com.example.sigmacapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    private String username;
    private String password;
    private String nombre;
    private String rol;
    private String iniciales;
    private String color;

    // Getters y Setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
    public String getIniciales() { return iniciales; }
    public String getColor() { return color; }
}