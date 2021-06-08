package br.com.zupedu.propostasComNoSql.handler;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;

public class ErroPersonalizado {
    @JsonProperty
    private String message;

    @JsonCreator
    public ErroPersonalizado(FieldError error) {
        this.message = "Campo: "+error.getField()+" "+message;
    }
}
