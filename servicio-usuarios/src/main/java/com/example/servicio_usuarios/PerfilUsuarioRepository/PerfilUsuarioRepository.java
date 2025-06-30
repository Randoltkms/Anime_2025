package com.example.servicio_usuarios.PerfilUsuarioRepository;

import com.example.servicio_usuarios.model.PerfilUsuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {
    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    Optional<PerfilUsuario> findByUsername(String username);
}