package com.gestor.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gestor.cobranca.model.StatusTitulo;
import com.gestor.cobranca.model.Titulo;
import com.gestor.cobranca.repository.Titulos;
import com.gestor.cobranca.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	
	
	public void salvar(Titulo titulo) throws IllegalAccessException {
		try {
			titulos.save(titulo);	
		}catch (DataIntegrityViolationException e) {
			throw new IllegalAccessException("Formato de data inválido");
		}
		
	}
	
	public Titulo editar(Long codigoTitulo) {
		Titulo titulo = titulos.getOne(codigoTitulo);
		return titulo;
	}
	
	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}

	public String receber(Long codigo) {
		Titulo titulo = titulos.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}
	
	public List<Titulo> filtrar(TituloFilter filtro){
		String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();		
		return titulos.findByDescricaoContaining(descricao);
	}

	
	
	
}
