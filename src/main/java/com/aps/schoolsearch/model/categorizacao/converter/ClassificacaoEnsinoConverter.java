package com.aps.schoolsearch.model.categorizacao.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;

@Converter(autoApply = true)
public class ClassificacaoEnsinoConverter implements AttributeConverter<ClassificacaoEnsino, String> {
	
    @Override
    public String convertToDatabaseColumn(ClassificacaoEnsino classificacao) {
    	if(classificacao == null) {
    		return null;
    	}
        return classificacao.getClassificacao();
    }
 
    @Override
    public ClassificacaoEnsino convertToEntityAttribute(String input) {
    	if(input == null) {
    		return null;
    	}
    	return Stream.of(ClassificacaoEnsino.values())
    	          .filter(c -> c.getClassificacao().equals(input))
    	          .findFirst()
    	          .orElseThrow(IllegalArgumentException::new);
    }
}