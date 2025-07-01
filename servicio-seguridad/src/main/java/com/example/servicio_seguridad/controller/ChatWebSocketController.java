package com.example.servicio_seguridad.controller;

import com.example.servicio_seguridad.model.Mensaje;
import com.example.servicio_seguridad.model.MensajeRespuesta;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatWebSocketController {

    @MessageMapping("/chat") // Cliente lo envía a: /app/chat
    @SendTo("/topic/mensajes") // Servidor envía a todos los que escuchan: /topic/mensajes
    public MensajeRespuesta procesarMensaje(Mensaje mensaje) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return new MensajeRespuesta(mensaje.getEmisor(), mensaje.getContenido(), hora);
    }
}
