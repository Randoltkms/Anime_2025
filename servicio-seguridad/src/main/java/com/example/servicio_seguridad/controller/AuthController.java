package com.example.servicio_seguridad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.servicio_seguridad.config.JwtConfig;
import com.example.servicio_seguridad.dto.AuthRequest;
import com.example.servicio_seguridad.dto.LoginResponse;
import com.example.servicio_seguridad.model.Usuario;
import com.example.servicio_seguridad.repository.UsuarioRepository;
import com.example.servicio_seguridad.security.DetallesUsuario;
import com.example.servicio_seguridad.service.RegistroService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final UsuarioRepository usuarioRepository;
    private final RegistroService registroService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtConfig jwtConfig,
                          UsuarioRepository usuarioRepository,
                          RegistroService registroService) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.usuarioRepository = usuarioRepository;
        this.registroService = registroService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );

            DetallesUsuario userDetails = (DetallesUsuario) authentication.getPrincipal();
            String token = jwtConfig.generateToken(userDetails.getUsername());

            // Obtener el usuario para acceder a los roles
            Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            List<String> roles = usuario.getRoles().stream()
                    .map(rol -> rol.getNombre())
                    .toList();

            LoginResponse response = new LoginResponse(token, usuario.getUsername(), roles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Error de autenticación: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        String resultado = registroService.registrarUsuario(request);
        return ResponseEntity.ok(resultado);
    }
}
