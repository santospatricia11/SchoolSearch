package com.aps.schoolsearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aps.schoolsearch.model.Escola;
import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;

public interface EscolaRepository extends JpaRepository<Escola, String>{
	
	@Query("SELECT * FROM escola e WHERE e.nome SIMILAR TO %?1% ")
	List<Escola> findEscolaByName (String name);
	
	@Query("SELECT * FROM escola e WHERE e.classificacao_ensino IN (:classificacoes)")
	List<Escola> findEscolaByClassificoesEnsino(@Param("classificacoes") List<ClassificacaoEnsino> classificacoes);
	
	@Query("SELECT * FROM escola e WHERE e.metodo_ensino IN (:metodos)")
	List<Escola> findEscolaByMetodosEnsino(@Param("metodos") List<MetodoEnsino> metodos);
	@Query("SELECT escola.* FROM escola e WHERE escola.cpnj = nivel_ensino.escola_cnpj AND nivel_ensino.nivel_ensino IN (:niveis)")
	List<Escola> findEscolaByNiveisEnsino(@Param("niveis") List<NivelEnsino> niveis);
	
	@Query("SELECT * FROM escola e WHERE e.cnpj = idioma.escola_cnpj AND idiomas.idioma IN (:idiomas)")
	List<Escola> findEscolaByIdiomas(@Param("idiomas") List<String> idiomas);
	
	@Query("SELECT * FROM escola e WHERE e.mensalidade <= %?1% ")
	List<Escola> findEscolaByNivelEnsino(Integer maximo);
	
}
