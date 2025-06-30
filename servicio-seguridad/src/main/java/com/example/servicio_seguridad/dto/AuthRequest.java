package com.example.servicio_seguridad.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDate fechaRegistro;
}