package com.aps.schoolsearch.model.categorizacao.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.aps.schoolsearch.model.categorizacao.MetodoEnsino;

@Converter(autoApply = true)
public class MetodoEnsinoConverter implements AttributeConverter<MetodoEnsino, String> {
	
    @Override
    public String convertToDatabaseColumn(MetodoEnsino classificacao) {
    	if(classificacao == null) {
    		return null;
    	}
        return classificacao.getMetodo();
    }
 
    @Override
    public MetodoEnsino convertToEntityAttribute(String input) {
    	if(input == null) {
    		return null;
    	}
    	return Stream.of(MetodoEnsino.values())
    	          .filter(c -> c.getMetodo().equals(input))
    	          .findFirst()
    	          .orElseThrow(IllegalArgumentException::new);
    }
}