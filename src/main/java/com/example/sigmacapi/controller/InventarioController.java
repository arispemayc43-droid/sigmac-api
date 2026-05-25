package com.example.sigmacapi.controller;

import com.example.sigmacapi.model.Inventario;
import com.example.sigmacapi.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping
    public List<Inventario> getAll() {
        return inventarioRepository.findAll();
    }

    @GetMapping("/stock-bajo")
    public List<Inventario> getStockBajo() {
        return inventarioRepository.findStockBajo();
    }

    @GetMapping("/tipo/{tipo}")
    public List<Inventario> getByTipo(@PathVariable String tipo) {
        return inventarioRepository.findByTipoMaterial(tipo);
    }

    @PostMapping
    public Inventario create(@RequestBody Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        inventarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}