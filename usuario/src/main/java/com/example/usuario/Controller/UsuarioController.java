package com.example.usuario.Controller;

import com.example.usuario.DTO.UsuarioDTO;
import com.example.usuario.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "API para la gestión de usuarios del gimnasio")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Listar usuarios", description = "Obtiene todos los usuarios registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<UsuarioDTO> listar(){

        return service.listar();
    }

    @Operation(summary = "Obtener usuario por ID", description = "Busca un usuario utilizando su identificador")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente")
    @PostMapping
    public UsuarioDTO crear(@Valid @RequestBody UsuarioDTO dto) {
        return service.crear(dto);
    }

    @Operation(summary = "Actualizar usuario", description = "Modifica la información de un usuario existente")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id,
                                 @Valid @RequestBody UsuarioDTO dto) {
        return service.actualizar(id, dto);
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su identificador")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}
