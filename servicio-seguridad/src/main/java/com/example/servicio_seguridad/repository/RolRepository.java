package com.example.servicio_seguridad.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.servicio_seguridad.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
	Optional<Rol> findByNombre(String nombre);
}