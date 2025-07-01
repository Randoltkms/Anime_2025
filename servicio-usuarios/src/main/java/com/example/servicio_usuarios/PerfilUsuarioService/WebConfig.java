package com.example.servicio_usuarios.PerfilUsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/usuarios/**")
                        .allowedOrigins("http://localhost:8080") // frontend/microservicio que hace la petición
                        .allowedMethods("POST", "GET", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
