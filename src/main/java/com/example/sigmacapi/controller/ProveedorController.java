package com.example.sigmacapi.controller;

import com.example.sigmacapi.model.Proveedor;
import com.example.sigmacapi.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public List<Proveedor> getAll() {
        return proveedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable Long id) {
        return proveedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proveedor create(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}