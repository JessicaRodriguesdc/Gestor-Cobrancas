package com.gestor.cobranca.titulo.service;

import java.util.List;

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

	//Servico para criar um novo titulo de servico
	public void salvar(Titulo titulo,Usuario usuario) throws IllegalAccessException {
		try {
			titulo.setUsuario(usuario);
			titulos.save(titulo);
		}catch (DataIntegrityViolationException e) {
			throw new IllegalAccessException("Formato de data inválido");
		}
		
	}

	//Servico para editar um novo titulo de servico
	public Titulo editar(Long codigoTitulo,Usuario usuario) {
		Titulo titulo = titulos.getOne(codigoTitulo);
		titulo.setUsuario(usuario);
		return titulo;
	}


	//Servico para excluir um novo titulo de servico
	public void excluir(Long codigo,Usuario usuario) {
		Titulo titulo = titulos.getOne(codigo);
		if (titulo.getUsuario().getId().equals(usuario.getId())){
			titulos.deleteById(codigo);
		}
	}

	//Servico para editar o status do titulo da cobranca para "RECEBIDO"
	public String receber(Long codigo, Usuario usuario) {
		Titulo titulo = titulos.getOne(codigo);

		if (titulo.getUsuario().getId().equals(usuario.getId())){
			titulo.setStatus(StatusTitulo.RECEBIDO);
			titulos.save(titulo);

			return StatusTitulo.RECEBIDO.getDescricao();
		}
		return StatusTitulo.PENDENTE.getDescricao();
	}

	//Servico para listar os titulos de cobranca pelo usuario
	public List<Titulo> filtrar(TituloFilter filtro, Usuario usuario){
		String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();		
		return titulos.findByDescricaoContainingAndUsuario(descricao,usuario);
	}
}
