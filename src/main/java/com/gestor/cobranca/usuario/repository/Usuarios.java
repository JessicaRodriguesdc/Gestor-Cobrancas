package com.gestor.cobranca.usuario.repository;

import com.gestor.cobranca.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuarios extends JpaRepository<Usuario, Long> {

    //Encontrar usuario pelo login e senha
    public Usuario findByLoginAndSenha(String login, String senha);
}
