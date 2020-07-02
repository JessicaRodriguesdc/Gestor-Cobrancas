package com.gestor.cobranca.service;

import com.gestor.cobranca.model.Usuario;
import com.gestor.cobranca.repository.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {

    @Autowired
    private Usuarios usuarios;



    public void salvar(Usuario usuario){
        usuarios.save(usuario);
    }
}
