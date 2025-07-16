package com.isil.edu.pe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isil.edu.pe.model.Usuarios;


public interface UsuariosRespository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByCorreoAndContrasena(String correo, String contrasena);
}