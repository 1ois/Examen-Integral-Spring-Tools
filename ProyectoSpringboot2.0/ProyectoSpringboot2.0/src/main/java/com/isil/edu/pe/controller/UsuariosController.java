package com.isil.edu.pe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.isil.edu.pe.exceptions.ResourceNotFoundException;
import com.isil.edu.pe.model.Usuarios;
import com.isil.edu.pe.repository.UsuariosRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173") // permite conexión con React
@RestController
@RequestMapping("/usuarios/controller")
public class UsuariosController {

    @Autowired
    private UsuariosRespository usuariosRepository;

    // Obtener todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    // Obtener usuario por ID
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuarios> getUsuariosById(@PathVariable(value = "id") Integer usuariosId)
            throws ResourceNotFoundException {
        Usuarios usuario = usuariosRepository.findById(usuariosId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no se encuentra por ID: " + usuariosId));
        return ResponseEntity.ok(usuario);
    }

    // Crear un nuevo usuario
    @PostMapping("/usuarios")
    public Usuarios createUsuarios(@Validated @RequestBody Usuarios usuarios) {
        usuarios.setTipousuario("USER"); // Siempre se crea como USER
        return usuariosRepository.save(usuarios);
    }

    // Login de usuario
    @PostMapping("/usuarios/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuarios usuarios) {
        Usuarios usuario = usuariosRepository.findByCorreoAndContrasena(
                usuarios.getCorreo(), usuarios.getContrasena());

        if (usuario != null) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Inicio de sesión exitoso");
            response.put("nombre", usuario.getNombre());  // Devolver nombre al frontend
            response.put("correo", usuario.getCorreo());
            response.put("tipousuario", usuario.getTipousuario());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales incorrectas"));
        }
    }

    // Actualizar usuario
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuarios> updateUsuarios(@PathVariable(value = "id") Integer usuariosId,
                                                   @Validated @RequestBody Usuarios usuariosDetail)
            throws ResourceNotFoundException {
        Usuarios usuario = usuariosRepository.findById(usuariosId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no ha sido encontrado por el ID: " + usuariosId));

        usuario.setNombre(usuariosDetail.getNombre());
        usuario.setCorreo(usuariosDetail.getCorreo());
        usuario.setContrasena(usuariosDetail.getContrasena());
        usuario.setTipousuario(usuariosDetail.getTipousuario());

        final Usuarios updateUsuario = usuariosRepository.save(usuario);
        return ResponseEntity.ok(updateUsuario);
    }

    // Eliminar usuario
    @DeleteMapping("/usuarios/{id}")
    public Map<String, Boolean> deleteUsuario(@PathVariable(value = "id") Integer usuariosId)
            throws ResourceNotFoundException {
        Usuarios usuario = usuariosRepository.findById(usuariosId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no se encuentra con el ID: " + usuariosId));

        usuariosRepository.delete(usuario);

        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
