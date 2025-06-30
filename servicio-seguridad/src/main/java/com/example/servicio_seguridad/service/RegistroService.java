package com.example.servicio_seguridad.service;

import com.example.servicio_seguridad.dto.AuthRequest;
import com.example.servicio_seguridad.dto.PerfilUsuarioDTO;
import com.example.servicio_seguridad.model.Usuario;
import com.example.servicio_seguridad.model.Rol;
import com.example.servicio_seguridad.repository.UsuarioRepository;
import com.example.servicio_seguridad.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistroService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioClient usuarioClient; // <-- Inyecta tu Feign Client

    /**
     * Registra un nuevo usuario con rol ROLE_USER.
     * @param request Datos de autenticación (username y password).
     * @return Mensaje de éxito o error.
     */
    public String registrarUsuario(AuthRequest request) {
        usuarioRepository.findByUsername(request.getUsername())
            .ifPresent(u -> { throw new IllegalStateException("El usuario ya existe"); });

        Rol rolUsuario = rolRepository.findByNombre("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("El rol ROLE_USER no existe"));

        Usuario nuevoUsuario = Usuario.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(List.of(rolUsuario))
            .build();

        usuarioRepository.save(nuevoUsuario);

        // Ahora crea el perfil en el microservicio de usuarios
        PerfilUsuarioDTO perfil = new PerfilUsuarioDTO();
        perfil.setUsername(request.getUsername());
        perfil.setNombre(request.getNombre()); // <-- AGREGA ESTO
        perfil.setEmail(request.getEmail());   // <-- AGREGA ESTO
        perfil.setTelefono(request.getTelefono()); // si lo tienes en AuthRequest
        perfil.setDireccion(request.getDireccion()); // si lo tienes en AuthRequest
        perfil.setFechaRegistro(LocalDate.now());
        // Puedes setear campos adicionales si los tienes en el AuthRequest

        usuarioClient.crearPerfil(perfil);

        return "Usuario registrado correctamente";
    }
}