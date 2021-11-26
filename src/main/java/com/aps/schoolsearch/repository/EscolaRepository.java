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
	
	@Query(nativeQuery=true, value="SELECT * FROM escola e WHERE e.nome SIMILAR TO %?1% ")
	List<Escola> findEscolaByName (String name);
	
	@Query(nativeQuery=true, value="SELECT * FROM escola WHERE classificacao_ensino IN (:classificacoes)")
	List<Escola> findEscolaByClassificoesEnsino(@Param("classificacoes") List<ClassificacaoEnsino> classificacoes);
	
	@Query(nativeQuery=true, value="SELECT * FROM escola e WHERE e.metodo_ensino IN (:metodos)")
	List<Escola> findEscolaByMetodosEnsino(@Param("metodos") List<MetodoEnsino> metodos);
	@Query(nativeQuery=true, value="SELECT e.* FROM escola e JOIN nivel_ensino ne ON e.cpnj = ne.escola_cnpj WHERE ne.nivel_ensino IN (:niveis)")
	List<Escola> findEscolaByNiveisEnsino(@Param("niveis") List<NivelEnsino> niveis);
	
	@Query(nativeQuery=true, value="SELECT e.* FROM escola e JOIN idioma i ON e.cnpj = i.escola_cnpj WHERE i.idioma IN (:idiomas)")
	List<Escola> findEscolaByIdiomas(@Param("idiomas") List<String> idiomas);
	
	@Query(nativeQuery=true, value="SELECT * FROM escola e WHERE e.mensalidade <= %?1% ")
	List<Escola> findEscolaByNivelEnsino(Integer maximo);
	
}
