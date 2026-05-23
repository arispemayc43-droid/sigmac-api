package com.example.sigmacapi.repository;

import com.example.sigmacapi.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    // Consulta optimizada: movimientos por tipo
    @Query("SELECT m FROM Movimiento m WHERE m.tipo = :tipo")
    List<Movimiento> findByTipo(String tipo);

    // Consulta optimizada: movimientos por obra
    @Query("SELECT m FROM Movimiento m WHERE m.obra.idObra = :idObra")
    List<Movimiento> findByObra(Long idObra);
}