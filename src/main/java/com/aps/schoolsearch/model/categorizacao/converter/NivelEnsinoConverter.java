package com.aps.schoolsearch.model.categorizacao.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.aps.schoolsearch.model.categorizacao.ClassificacaoEnsino;
import com.aps.schoolsearch.model.categorizacao.NivelEnsino;

@Converter(autoApply = true)
public class NivelEnsinoConverter implements AttributeConverter<NivelEnsino, String> {
	
    @Override
    public String convertToDatabaseColumn(NivelEnsino classificacao) {
    	if(classificacao == null) {
    		return null;
    	}
        return classificacao.getNivel();
    }
 
    @Override
    public NivelEnsino convertToEntityAttribute(String input) {
    	if(input == null) {
    		return null;
    	}
    	return Stream.of(NivelEnsino.values())
    	          .filter(c -> c.getNivel().equals(input))
    	          .findFirst()
    	          .orElseThrow(IllegalArgumentException::new);
    }
}