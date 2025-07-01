package com.example.servicio_seguridad.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensajeRespuesta {
    private String emisor;
    private String contenido;
    private String hora;
}
