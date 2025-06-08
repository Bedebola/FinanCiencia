package com.financiencia.controllers;
import com.financiencia.entities.Cidade;
import com.financiencia.entities.Projeto;
import com.financiencia.repositories.CidadeRepository;
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
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Cidade>> listarCidades () {
        List<Cidade> listagemCidades = cidadeRepository.findAll(Sort.by("id").ascending());
        return ResponseEntity.ok().body(listagemCidades);
    }

}
