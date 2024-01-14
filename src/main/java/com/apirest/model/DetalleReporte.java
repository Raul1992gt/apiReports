package com.apirest.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "detalles_reporte")
@Data
public class DetalleReporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP")
    private Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoDetalle estado;

    @ManyToOne
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    public enum EstadoDetalle {
        PENDIENTE,
        COMPLETADO,
        EN_PROGRESO,
        CANCELADO
    }
}
