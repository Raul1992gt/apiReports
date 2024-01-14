package com.apirest.controller;

import com.apirest.model.Usuario;
import com.apirest.service.UsuarioService;
import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportarPdf() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "LISTADO_USUARIOS.pdf");

        try {
            List<Usuario> listaUsuarios = usuarioService.getAll();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaUsuarios);

            System.out.println(dataSource);

            byte[] pdfBytes = usuarioService.exportarPdf(dataSource);

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportarXls() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("LISTADO_USUARIOS.xlsx").build();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok().headers(headers).body(usuarioService.exportarXls());
    }


}
