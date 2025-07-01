package com.example.servicio_seguridad.controller;

import com.example.servicio_seguridad.dto.AuthRequest;
import com.example.servicio_seguridad.service.RegistroService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class RegistroController {

    private final RegistroService registroService;
    
    @GetMapping("/register")

    public String mostrarFormularioLogin() {
        return "registro";
    }

    @PostMapping("/auth/form-registro")
    public String registrarDesdeFormulario(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam String nombre,
                                           @RequestParam String email,
                                           @RequestParam(required = false) String telefono,
                                           @RequestParam(required = false) String direccion,
                                           Model model) {
        try {
            AuthRequest request = new AuthRequest();
            request.setUsername(username);
            request.setPassword(password);
            request.setNombre(nombre);
            request.setEmail(email);
            request.setTelefono(telefono);
            request.setDireccion(direccion);
            request.setFechaRegistro(LocalDate.now());

            registroService.registrarUsuario(request);
            return "redirect:/login"; // Redirige al login tras éxito
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            return "registro";
        }
    }
}
