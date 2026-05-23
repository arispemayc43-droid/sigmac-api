package com.example.sigmacapi.repository;

import com.example.sigmacapi.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Long> {
}