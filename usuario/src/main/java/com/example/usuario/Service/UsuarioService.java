package com.example.usuario.Service;

import com.example.usuario.DTO.UsuarioDTO;
import com.example.usuario.Entity.Usuario;
import com.example.usuario.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    // LISTAR
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // OBTENER POR ID
    public UsuarioDTO obtenerPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return convertirADTO(usuario);
    }

    // CREAR
    public UsuarioDTO crear(UsuarioDTO dto) {

        Usuario usuario = convertirAEntidad(dto);

        Usuario guardado = usuarioRepository.save(usuario);

        return convertirADTO(guardado);
    }

    // ACTUALIZAR
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setUsername(dto.getUsername());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());

        Usuario actualizado = usuarioRepository.save(usuario);

        return convertirADTO(actualizado);
    }

    // ELIMINAR
    public void eliminar(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }

        usuarioRepository.deleteById(id);
    }

    // BUSCAR POR USERNAME (ENTITY)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // ======================
    // MAPPERS (SOLO INTERNO)
    // ======================

    private UsuarioDTO convertirADTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());

        return dto;
    }

    private Usuario convertirAEntidad(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());

        return usuario;
    }

    public UsuarioDTO buscarPorUsernameDTO(String username) {

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return convertirADTO(usuario);
    }
}
