package com.apirest.service;

import com.apirest.model.Usuario;
import com.apirest.repository.UsuarioRepository;
import com.apirest.utils.UsuariosJasperReportGenerator;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private UsuariosJasperReportGenerator usuariosJasperReportGenerator;

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

    public byte[] exportarPdf(JRBeanCollectionDataSource dataSource) throws Exception {
        return usuariosJasperReportGenerator.exportToPdf(dataSource);
    }

    public byte[] exportarXls() throws Exception {
        return usuariosJasperReportGenerator.exportToXls(usuarioRepository.findAll());
    }

}
