package com.example.servicio_seguridad.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // Elimina todos los atributos de sesión
        return "redirect:/";  // Redirige a la página de inicio
    }
}
