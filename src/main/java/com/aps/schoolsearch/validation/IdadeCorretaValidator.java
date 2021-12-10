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
	        String objeto = obj.toString();
	        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
	        String hoje = LocalDate.now().format(formatador);
	        LocalDate dataAtual= LocalDate.parse(hoje, formatador);
	        LocalDate dataNascimento = LocalDate.parse(objeto, formatador);
	        
	        Integer intervalo = Period.between(dataNascimento, dataAtual).getYears();
	        
	        return intervalo >= 18;
    	}catch(DateTimeParseException exception) {
    	}
    	return false;
    }
}

