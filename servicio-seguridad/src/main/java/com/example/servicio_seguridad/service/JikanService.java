package com.example.servicio_seguridad.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class JikanService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL = "https://api.jikan.moe/v4";

    public List<AnimeDTO> obtenerTopAnimes() {
        try {
            JikanResponse response = restTemplate.getForObject(BASE_URL + "/top/anime", JikanResponse.class);
            return response != null ? response.getData() : Collections.emptyList();
        } catch (Exception e) {
            log.error("Error al obtener top animes: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<AnimeDTO> buscarPorNombre(String nombre) {
        try {
            String url = BASE_URL + "/anime?q=" + nombre + "&limit=10";
            JikanResponse response = restTemplate.getForObject(url, JikanResponse.class);
            return response != null ? response.getData() : Collections.emptyList();
        } catch (Exception e) {
            log.error("Error al buscar animes: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<AnimeDTO> buscarPorGenero(String genero) {
        try {
            // Ejemplo con acción (ID 1) → ver ID reales en doc: https://docs.api.jikan.moe/#tag/anime/operation/getAnime
            String url = BASE_URL + "/anime?genres=" + genero + "&limit=10";
            JikanResponse response = restTemplate.getForObject(url, JikanResponse.class);
            return response != null ? response.getData() : Collections.emptyList();
        } catch (Exception e) {
            log.error("Error al filtrar por género: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // DTOs internos
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JikanResponse {
        private List<AnimeDTO> data;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AnimeDTO {
        private int mal_id;
        private String title;

        private Images images;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @Data
        public static class Images {
            private Jpg jpg;

            @Data
            public static class Jpg {
                private String image_url;
            }
        }

        private String synopsis;
    }
}
