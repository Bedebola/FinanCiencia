package com.financiencia.data;

import com.financiencia.entities.Administrador;
import com.financiencia.repositories.AdministradorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdministradorData {

    private final AdministradorRepository administradorRepository;


    public AdministradorData(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @PostConstruct

    public void LoadAdmnistradorData (){

        if (administradorRepository.count() == 0) {

            List<Administrador> administrador = List.of(

                    new Administrador("berenice@gmail.com", "123456")
            );

            administradorRepository.saveAll(administrador);

        }
    }
}
