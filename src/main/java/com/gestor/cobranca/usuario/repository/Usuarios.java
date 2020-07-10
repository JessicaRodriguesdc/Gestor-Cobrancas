package com.gestor.cobranca.usuario.repository;

import com.gestor.cobranca.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuarios extends JpaRepository<Usuario, Long> {

    public Usuario findByLoginAndSenha(String login, String senha);
}
