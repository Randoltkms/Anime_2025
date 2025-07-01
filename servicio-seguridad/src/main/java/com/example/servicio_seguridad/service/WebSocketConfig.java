package com.example.servicio_seguridad.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Permite enviar mensajes desde servidor a cliente
        config.enableSimpleBroker("/topic");

        // Prefijo para enviar mensajes desde cliente al servidor
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint donde se conectan los clientes
        registry.addEndpoint("/chat-websocket").setAllowedOriginPatterns("*").withSockJS();
    }
}
