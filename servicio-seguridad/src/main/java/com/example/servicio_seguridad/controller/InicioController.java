// InicioController.java
package com.example.servicio_seguridad.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.servicio_seguridad.service.JikanService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InicioController {

    private final JikanService jikanService;

    @GetMapping("/")
    public String inicio(@RequestParam(required = false) String q,
                         @RequestParam(required = false) String categoria,
                         Model model,
                         HttpSession session) {
        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");

        model.addAttribute("username", username);
        model.addAttribute("email", email);

        List<JikanService.AnimeDTO> animes;
        if (q != null && !q.isEmpty()) {
            animes = jikanService.buscarPorNombre(q);
        } else if (categoria != null && !categoria.isEmpty()) {
            animes = jikanService.buscarPorGenero(categoria); // CAMBIADO AQUÍ
        } else {
            animes = jikanService.obtenerTopAnimes();
        }

        model.addAttribute("animes", animes);
        return "index";
    }

    @Data
    @AllArgsConstructor
    public static class Anime {
        private String titulo;
        private String imagenUrl;
        private String descripcion;
    }
}
