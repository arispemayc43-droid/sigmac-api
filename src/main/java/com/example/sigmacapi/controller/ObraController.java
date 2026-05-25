package com.example.sigmacapi.controller;

import com.example.sigmacapi.model.Obra;
import com.example.sigmacapi.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/obras")
@CrossOrigin(origins = "*")
public class ObraController {

    @Autowired
    private ObraRepository obraRepository;

    @GetMapping
    public List<Obra> getAll() {
        return obraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> getById(@PathVariable Long id) {
        return obraRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Obra create(@RequestBody Obra obra) {
        return obraRepository.save(obra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        obraRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}