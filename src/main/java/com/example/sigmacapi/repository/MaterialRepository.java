package com.example.sigmacapi.repository;

import com.example.sigmacapi.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}