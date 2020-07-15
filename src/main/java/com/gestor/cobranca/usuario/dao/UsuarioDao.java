package com.gestor.cobranca.usuario.dao;

import javax.validation.constraints.Size;

public class UsuarioDao {

    private String nome;
    @Size(max = 50)
    private String email;
    @Size(max = 50)
    private String login;
    @Size(max = 50)
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
