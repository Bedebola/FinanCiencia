package com.financiencia.controllers;

import com.financiencia.entities.Administrador;
import com.financiencia.repositories.AdministradorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/financiencia")
public class AcessController {

@Autowired
    AdministradorRepository administradorRepository;

@PostMapping("/login")
public ResponseEntity<?> loginOperador(
        @RequestBody Administrador administrador
) {
    Administrador loginValidacao = administradorRepository.findByEmail(administrador.getEmail());
    {

        if (loginValidacao == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        } else if (Objects.equals(loginValidacao.getEmail(), administrador.getEmail()) &&
                Objects.equals(loginValidacao.getSenha(), administrador.getSenha())) {
            return ResponseEntity.ok().body("Login autorizado!");
        } else {
            return ResponseEntity.badRequest().body("Login inválido!");
        }

    }

}
}
