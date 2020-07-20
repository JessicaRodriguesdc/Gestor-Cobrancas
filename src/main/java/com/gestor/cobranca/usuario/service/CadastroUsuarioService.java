package com.gestor.cobranca.usuario.service;

import com.gestor.cobranca.usuario.entity.Usuario;
import com.gestor.cobranca.usuario.repository.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {

    @Autowired
    private Usuarios usuarios;

    //Servico para criar um novo usuario
    public void salvar(Usuario usuario){
        usuarios.save(usuario);
    }
}
