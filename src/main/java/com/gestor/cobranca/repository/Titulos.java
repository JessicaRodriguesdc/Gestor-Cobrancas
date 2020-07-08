package com.gestor.cobranca.repository;

import java.util.List;

import com.gestor.cobranca.model.StatusTitulo;
import com.gestor.cobranca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestor.cobranca.model.Titulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Titulos extends JpaRepository<Titulo, Long> {
	
	public List<Titulo> findByDescricaoContainingAndUsuario(String descricao, Usuario usuario);

	//@Query("select count(t.status) from Titulo as t join Usuario as u on t.usuario = u.id where t.status like :statu")

	@Query("select count(t.status), u.id from Titulo as t join Usuario as u on t.usuario = u.id " +
			"where u.id = :idUser and t.status like :statu group by u.id")
	long contarStatu(@Param("statu") StatusTitulo statu,@Param("idUser") Long idUser);
}
