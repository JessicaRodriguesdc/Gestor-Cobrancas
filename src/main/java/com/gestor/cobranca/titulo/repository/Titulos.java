package com.gestor.cobranca.titulo.repository;

import java.util.List;

import com.gestor.cobranca.titulo.entity.StatusTitulo;
import com.gestor.cobranca.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestor.cobranca.titulo.entity.Titulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Titulos extends JpaRepository<Titulo, Long> {

	//Lista titulos de cobranca pela descricao e usuario
	public List<Titulo> findByDescricaoContainingAndUsuario(String descricao, Usuario usuario);

	//Pega a quantidade de titulos que sao pendentes ou recebidos
	@Query("select count(t.status), u.id from Titulo as t join Usuario as u on t.usuario = u.id " +
			"where u.id = :idUser and t.status like :statu group by u.id")
	Long contarStatu(@Param("statu") StatusTitulo statu,@Param("idUser") Long idUser);

	//Mostra se existe usuario
	boolean existsByUsuario(Usuario usuario);

	//Mostra se existe status naquele usuario
	boolean existsByStatusAndUsuario(StatusTitulo statu,Usuario usuario);

}
