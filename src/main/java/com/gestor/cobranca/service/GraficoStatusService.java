package com.gestor.cobranca.service;
import com.gestor.cobranca.model.StatusTitulo;
import com.gestor.cobranca.repository.Titulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraficoStatusService {
    @Autowired
    private Titulos titulos;

    public Long pendente() {
        return titulos.contarStatu(StatusTitulo.PENDENTE);
    }

    public Long recebido(){
        return titulos.contarStatu(StatusTitulo.RECEBIDO);
    }

}
