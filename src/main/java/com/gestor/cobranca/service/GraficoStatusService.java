package com.gestor.cobranca.service;
import com.gestor.cobranca.model.StatusTitulo;
import com.gestor.cobranca.model.Usuario;
import com.gestor.cobranca.repository.Titulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraficoStatusService {
    @Autowired
    private Titulos titulos;

    public Long pendente(Usuario usuario) {
        return titulos.contarStatu(StatusTitulo.PENDENTE,usuario.getId());
    }

    public Long recebido(Usuario usuario){
        return titulos.contarStatu(StatusTitulo.RECEBIDO,usuario.getId());
    }

}
