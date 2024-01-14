package com.apirest.utils;

import com.apirest.model.Usuario;
import com.apirest.repository.UsuarioRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuariosJasperReportGenerator {

    public byte[] exportToPdf(JRBeanCollectionDataSource dataSource) throws Exception {
        return JasperExportManager.exportReportToPdf(getReport(dataSource));
    }

    public byte[] exportToXls(List<Usuario> listaUsuarios) throws Exception {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();

       // exporter.setExporterInput(new SimpleExporterInput(getReport(listaUsuarios)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getReport(JRBeanCollectionDataSource dataSource)  throws FileNotFoundException, JRException {
        String jrxmlPath = ResourceUtils.getFile("classpath:LISTADO_USUARIOS.jrxml").getAbsolutePath();

        JasperPrint report;
        report = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(jrxmlPath),
                new HashMap<>(),
                dataSource
        );

        return report;
    }
}
