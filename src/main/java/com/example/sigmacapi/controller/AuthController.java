package com.example.sigmacapi.controller;

import com.example.sigmacapi.model.Usuario;
import com.example.sigmacapi.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
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

        String hashGuardado = user.get().getPassword();

        // Verificar si la contraseña ya está hasheada con BCrypt
        boolean passwordValida;
        if (hashGuardado.startsWith("$2a$") || hashGuardado.startsWith("$2b$")) {
            passwordValida = BCrypt.checkpw(password, hashGuardado);
        } else {
            // Contraseña en texto plano — la encriptamos y guardamos
            passwordValida = hashGuardado.equals(password);
            if (passwordValida) {
                String nuevoHash = BCrypt.hashpw(password, BCrypt.gensalt());
                user.get().setPassword(nuevoHash);
                usuarioRepo.save(user.get());
            }
        }

        if (!passwordValida) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (usuarioRepo.existsById(username)) {
            return ResponseEntity.status(400).body("Usuario ya existe");
        }

        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        nuevo.setNombre(body.get("nombre"));
        nuevo.setRol(body.get("rol"));
        nuevo.setIniciales(body.get("iniciales"));
        nuevo.setColor(body.get("color"));

        usuarioRepo.save(nuevo);
        return ResponseEntity.ok("Usuario creado correctamente");
    }
}