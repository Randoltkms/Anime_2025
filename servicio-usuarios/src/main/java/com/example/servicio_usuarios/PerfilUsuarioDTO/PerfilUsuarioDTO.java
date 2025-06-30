package com.example.servicio_usuarios.PerfilUsuarioDTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PerfilUsuarioDTO {
    private String username;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDate fechaRegistro;
}