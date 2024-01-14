package com.apirest.controller;

import com.apirest.model.Usuario;
import com.apirest.service.UsuarioService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Data
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll(){
        try {
            List<Usuario> usuarios = usuarioService.getAll();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            usuarioService.create(usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario");
        }
    }


}
