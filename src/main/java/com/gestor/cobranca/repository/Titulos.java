package com.gestor.cobranca.repository;

import java.util.List;

import com.gestor.cobranca.model.StatusTitulo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestor.cobranca.model.Titulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Titulos extends JpaRepository<Titulo, Long> {
	
	public List<Titulo> findByDescricaoContaining(String descricao);

	@Query("select count(status) from Titulo where status like :statu")
	long contarStatu(@Param("statu") StatusTitulo statu);
	
}
