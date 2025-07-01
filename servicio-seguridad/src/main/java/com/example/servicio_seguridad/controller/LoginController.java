package com.example.servicio_seguridad.controller;

import com.example.servicio_seguridad.config.JwtConfig;
import com.example.servicio_seguridad.model.Usuario;
import com.example.servicio_seguridad.repository.UsuarioRepository;
import com.example.servicio_seguridad.security.DetallesUsuario;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtConfig jwtConfig;

    // 👉 Nuevo: Muestra el formulario login.html
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @PostMapping("/auth/form-login")
    public String loginDesdeFormulario(@RequestParam String username,
                                       @RequestParam String password,
                                       HttpSession session,
                                       Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            DetallesUsuario userDetails = (DetallesUsuario) authentication.getPrincipal();

            Usuario usuario = usuarioRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            session.setAttribute("username", usuario.getUsername());
            session.setAttribute("email", "correo@example.com");

            String token = jwtConfig.generateToken(usuario.getUsername());
            session.setAttribute("token", token);

            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }
}
