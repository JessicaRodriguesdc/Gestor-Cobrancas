package com.gestor.cobranca.repository;

import com.gestor.cobranca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuarios extends JpaRepository<Usuario, Long> {

    public Usuario findByLoginAndSenha(String login, String senha);
}
