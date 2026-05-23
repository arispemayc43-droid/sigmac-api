package com.example.sigmacapi.repository;

import com.example.sigmacapi.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    // Consulta optimizada 1: materiales con stock bajo
    @Query("SELECT i FROM Inventario i WHERE i.cantidadDisponible <= i.stockMinimo")
    List<Inventario> findStockBajo();

    // Consulta optimizada 2: inventario por tipo de material
    @Query("SELECT i FROM Inventario i WHERE i.material.tipo = :tipo")
    List<Inventario> findByTipoMaterial(String tipo);
}