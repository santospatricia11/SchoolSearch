package com.aps.schoolsearch.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.aps.schoolsearch.model.dto.UsuarioPostDto;

public class SenhaCorrespondeValidator implements ConstraintValidator<SenhaCorresponde, Object> {
    
    @Override
    public void initialize(SenhaCorresponde constraintAnnotation) {
    	// não quero inicializar nada
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UsuarioPostDto user = (UsuarioPostDto) obj;
        
        Boolean isValid = user.getSenha().equals(user.getConfirmarSenha());
        
        if(Boolean.FALSE.equals(isValid)) {
        	context.disableDefaultConstraintViolation();
        	context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode("confirmarSenha").addConstraintViolation();
        }
        return isValid;
    }
}
