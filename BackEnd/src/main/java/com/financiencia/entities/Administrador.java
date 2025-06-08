package com.financiencia.entities;

import jakarta.persistence.*;

@Entity
public class Administrador {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

@Column(unique = true)
    private String email;

@Column
    private String senha;

    public Administrador() {
    }

    public Administrador(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}





