package com.mariots.biblioteca.bibliotecarest.api.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class TextoValidaciones
        implements ConstraintValidator<TextoRestricciones, Object>{

    private String longitud;
    private String textoString;

    @Override
    public void initialize(TextoRestricciones constraintAnnotation) {
        this.longitud = constraintAnnotation.nombreCampoLongitud();
        this.textoString = constraintAnnotation.nombreCampoTextoString();
    }

    @Override
    public boolean isValid(Object textoModel,
                           ConstraintValidatorContext context) {

        String valorLongitud = (String) new BeanWrapperImpl(textoModel)
                .getPropertyValue(longitud);
        String valorTextoString = (String) new BeanWrapperImpl(textoModel)
                .getPropertyValue(textoString);

        if(Objects.equals(valorLongitud,"breve")&& valorTextoString.length()>=120){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Un texto con longitud \"breve\" no puede tener más de 120 caracteres.").addConstraintViolation();
            return false;
        }

        if (Objects.equals(valorLongitud,"largo")&&valorTextoString.length()<=90) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Un texto con longitud \"largo\" no puede tener menos de 90 caracteres.").addConstraintViolation();
            return false;
        }

        if (Objects.equals(valorLongitud,"breve")==false&&Objects.equals(valorLongitud,"largo")==false&&Objects.nonNull(valorLongitud)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El campo longitud debe ser: \"breve\" o \"largo\".").addConstraintViolation();
            return false;
        }

        return true;
    }

}
