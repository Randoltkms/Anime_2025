package com.example.servicio_usuarios.UsuarioPerfilController;


import com.example.servicio_usuarios.PerfilUsuarioDTO.PerfilUsuarioDTO;
import com.example.servicio_usuarios.PerfilUsuarioService.PerfilUsuarioService;
import com.example.servicio_usuarios.model.PerfilUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioPerfilController {

    private final PerfilUsuarioService perfilUsuarioService;

    @PostMapping
    public ResponseEntity<PerfilUsuario> crearPerfil(@RequestBody PerfilUsuarioDTO dto) {
        PerfilUsuario perfil = perfilUsuarioService.crearPerfil(dto);
        return ResponseEntity.ok(perfil);
    }
}