package com.example.servicio_seguridad.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.servicio_seguridad.dto.PerfilUsuarioDTO;

@FeignClient(name = "servicio-usuarios", url = "http://localhost:8081")
public interface UsuarioClient {
    @PostMapping("/usuarios")
    PerfilUsuarioDTO crearPerfil(@RequestBody PerfilUsuarioDTO perfilUsuarioDTO);
}