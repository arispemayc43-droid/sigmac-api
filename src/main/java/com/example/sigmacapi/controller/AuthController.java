package com.example.sigmacapi.controller;

import com.example.sigmacapi.model.Usuario;
import com.example.sigmacapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        Optional<Usuario> user = usuarioRepo.findById(username);

        if (user.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
        if (!user.get().getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        return ResponseEntity.ok(user.get());
    }
}