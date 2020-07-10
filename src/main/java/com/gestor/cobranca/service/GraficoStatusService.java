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
        if(titulos.existsByUsuario(usuario)){
            if(titulos.existsByStatusAndUsuario(StatusTitulo.PENDENTE,usuario)) {
                Long pen = titulos.contarStatu(
                        StatusTitulo.PENDENTE, usuario.getId());
                System.out.println(pen);
                return pen;
            }
            return 0L;
        }
        return 0L;
    }

    public Long recebido(Usuario usuario) {
        if (titulos.existsByUsuario(usuario)) {
            if(titulos.existsByStatusAndUsuario(StatusTitulo.RECEBIDO,usuario)){
                Long rec =  titulos.contarStatu(
                        StatusTitulo.RECEBIDO, usuario.getId());
                return rec;
            }
            return 0L;
        }
        return 0L;
    }
}
