package com.example.servicio_usuarios.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuarios_perfil")
public class PerfilUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Debe coincidir con el username del microservicio de seguridad

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String telefono;

    @Column
    private String direccion;

    @Column
    private LocalDate fechaRegistro; // Fecha en que se unió el usuario

    // Puedes agregar más campos: carrera, tipo de usuario (estudiante, docente), etc.
}