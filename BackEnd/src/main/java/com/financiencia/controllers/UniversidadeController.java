package com.financiencia.controllers;

import com.financiencia.entities.Universidade;
import com.financiencia.repositories.UniversidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/universidades")
public class UniversidadeController {

    @Autowired
    private UniversidadeRepository universidadeRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Universidade>> listarUniversidades() {
        List<Universidade> listagemUniversidades = universidadeRepository.findAll(Sort.by("id").ascending());
        return ResponseEntity.ok().body(listagemUniversidades);
    }
}
