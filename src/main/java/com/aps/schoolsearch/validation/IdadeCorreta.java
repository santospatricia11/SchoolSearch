package com.aps.schoolsearch.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdadeCorretaValidator.class)
@Documented
public @interface IdadeCorreta {
	String message() default "A idade não pode ser menor que 18 anos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
