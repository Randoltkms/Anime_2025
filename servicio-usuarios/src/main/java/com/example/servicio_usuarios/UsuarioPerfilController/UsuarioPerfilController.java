package com.example.servicio_usuarios.UsuarioPerfilController;

import com.example.servicio_usuarios.PerfilUsuarioDTO.PerfilUsuarioDTO;
import com.example.servicio_usuarios.PerfilUsuarioService.PerfilUsuarioService;
import com.example.servicio_usuarios.model.PerfilUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios") // Ruta base: /usuarios
@RequiredArgsConstructor
public class UsuarioPerfilController {

    private final PerfilUsuarioService perfilUsuarioService;

    // POST /usuarios
    @PostMapping("") // Evita que se convierta en /usuarios/usuarios
    public ResponseEntity<PerfilUsuario> crearPerfil(@RequestBody PerfilUsuarioDTO dto) {
        PerfilUsuario perfil = perfilUsuarioService.crearPerfil(dto);
        return ResponseEntity.ok(perfil);
    }

    // GET /usuarios/{username}
    @GetMapping("/{username}")
    public ResponseEntity<PerfilUsuarioDTO> obtenerPerfil(@PathVariable String username) {
        PerfilUsuarioDTO dto = perfilUsuarioService.obtenerPerfilPorUsername(username);
        return ResponseEntity.ok(dto);
    }
}
