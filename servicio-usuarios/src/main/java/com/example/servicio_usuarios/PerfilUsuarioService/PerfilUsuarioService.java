	package com.example.servicio_usuarios.PerfilUsuarioService;
	
	
	import com.example.servicio_usuarios.PerfilUsuarioDTO.PerfilUsuarioDTO;
	import com.example.servicio_usuarios.PerfilUsuarioRepository.PerfilUsuarioRepository;
	import com.example.servicio_usuarios.model.PerfilUsuario;
	import org.springframework.stereotype.Service;
	import lombok.RequiredArgsConstructor;
	
	@Service
	@RequiredArgsConstructor
	public class PerfilUsuarioService {
		
		//Aquí va la lógica para crear y consultar usuarios.
	
	    private final PerfilUsuarioRepository perfilUsuarioRepository;
	
	    public PerfilUsuario crearPerfil(PerfilUsuarioDTO dto) {
	        PerfilUsuario perfil = new PerfilUsuario();
	        perfil.setUsername(dto.getUsername());
	        perfil.setNombre(dto.getNombre());
	        perfil.setEmail(dto.getEmail());
	        perfil.setTelefono(dto.getTelefono());
	        perfil.setDireccion(dto.getDireccion());
	        perfil.setFechaRegistro(dto.getFechaRegistro());
	        // Otros campos según tu modelo
	
	        return perfilUsuarioRepository.save(perfil);
	    }
	    
	    public PerfilUsuarioDTO obtenerPerfilPorUsername(String username) {
	        PerfilUsuario perfil = perfilUsuarioRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("Perfil no encontrado: " + username));

	        PerfilUsuarioDTO dto = new PerfilUsuarioDTO();
	        dto.setUsername(perfil.getUsername());
	        dto.setNombre(perfil.getNombre());
	        dto.setEmail(perfil.getEmail());
	        dto.setTelefono(perfil.getTelefono());
	        dto.setDireccion(perfil.getDireccion());
	        dto.setFechaRegistro(perfil.getFechaRegistro());
	        return dto;
	    }
	}