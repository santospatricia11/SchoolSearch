package com.aps.schoolsearch.validation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdadeCorretaValidator implements ConstraintValidator<IdadeCorreta, Object> {
    
    @Override
    public void initialize(IdadeCorreta constraintAnnotation) {
      // Padrão do constraint validator
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
    	try {
	        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
    		LocalDate dataAtual;
    		LocalDate dataNascimento;
    		
	        String hoje = LocalDate.now().format(formatador);
	        dataAtual= LocalDate.parse(hoje, formatador);
	        
    		if(obj instanceof String) {
    	        String objeto = obj.toString();
    	        dataNascimento = LocalDate.parse(objeto, formatador);
    		} else {
    			dataNascimento = (LocalDate) obj;
    		}
    		
	        Integer intervalo = Period.between(dataNascimento, dataAtual).getYears();
	        
	        return intervalo >= 18;
    	}catch(DateTimeParseException exception) {
    		
    	}
    	return false;
    }
}

