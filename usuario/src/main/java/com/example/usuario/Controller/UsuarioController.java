package com.example.usuario.Controller;

import com.example.usuario.DTO.UsuarioDTO;
import com.example.usuario.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public UsuarioDTO crear(@Valid @RequestBody UsuarioDTO dto) {
        return service.crear(dto);
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id,
                                 @Valid @RequestBody UsuarioDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}
