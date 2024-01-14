package com.apirest.service;

import com.apirest.model.Usuario;
import com.apirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean existsByCorreo(String correo) {
        return this.usuarioRepository.existsByCorreo(correo);
    }

    public Usuario findByCorreo(String correo) {
        return this.usuarioRepository.findByCorreo(correo);
    }

    public Usuario create(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public void delete(Usuario usuario) {
        this.usuarioRepository.delete(usuario);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
}
