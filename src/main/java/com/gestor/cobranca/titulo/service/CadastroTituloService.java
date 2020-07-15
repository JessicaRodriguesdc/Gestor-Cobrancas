package com.gestor.cobranca.titulo.service;

import java.util.List;

import com.gestor.cobranca.configuracao.Util;
import com.gestor.cobranca.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gestor.cobranca.titulo.entity.StatusTitulo;
import com.gestor.cobranca.titulo.entity.Titulo;
import com.gestor.cobranca.titulo.repository.Titulos;
import com.gestor.cobranca.titulo.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo,Usuario usuario) throws IllegalAccessException {
		try {
			Util util = new Util();

			titulo.setUsuario(usuario);
			titulo.setDataCobranca(util.dataCadastro());
			titulos.save(titulo);	
		}catch (DataIntegrityViolationException e) {
			throw new IllegalAccessException("Formato de data inválido");
		}
		
	}
	
	public Titulo editar(Long codigoTitulo,Usuario usuario) {
		Titulo titulo = titulos.getOne(codigoTitulo);
		titulo.setUsuario(usuario);
		return titulo;
	}
	
	public void excluir(Long codigo,Usuario usuario) {
		Titulo titulo = titulos.getOne(codigo);
		if (titulo.getUsuario().getId().equals(usuario.getId())){
			titulos.deleteById(codigo);
		}
	}

	public String receber(Long codigo, Usuario usuario) {
		Titulo titulo = titulos.getOne(codigo);

		if (titulo.getUsuario().getId().equals(usuario.getId())){
			titulo.setStatus(StatusTitulo.RECEBIDO);
			titulos.save(titulo);

			return StatusTitulo.RECEBIDO.getDescricao();
		}
		return StatusTitulo.PENDENTE.getDescricao();
	}
	
	public List<Titulo> filtrar(TituloFilter filtro, Usuario usuario){
		String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();		
		return titulos.findByDescricaoContainingAndUsuario(descricao,usuario);
	}
}
