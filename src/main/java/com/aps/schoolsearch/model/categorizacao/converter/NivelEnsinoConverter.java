package com.aps.schoolsearch.model.categorizacao.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.aps.schoolsearch.model.categorizacao.NivelEnsino;

@Converter(autoApply = true)
public class NivelEnsinoConverter implements AttributeConverter<NivelEnsino, String> {
	
    @Override
    public String convertToDatabaseColumn(NivelEnsino nivel) {
    	if(nivel== null) {
    		return null;
    	}
        return nivel.getNivel();
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