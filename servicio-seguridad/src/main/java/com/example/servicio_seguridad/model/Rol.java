package com.example.servicio_seguridad.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Rol implements GrantedAuthority {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Override
    public String getAuthority() {
        return nombre;
    }
}