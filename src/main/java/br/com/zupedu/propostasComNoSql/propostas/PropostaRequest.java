package br.com.zupedu.propostasComNoSql.propostas;

import br.com.zupedu.propostasComNoSql.validator.CPForCNPJ;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class PropostaRequest {

    @CPForCNPJ
    @JsonProperty
    private String documento;
    @Email
    @NotBlank
    @JsonProperty
    private String email;
    @NotBlank
    @JsonProperty
    private String nome;
    @NotBlank
    @JsonProperty
    private String endereco;
    @NotNull
    @JsonProperty
    @PositiveOrZero
    private BigDecimal salario;

    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {

        this.documento = documento.replaceAll("[^0-9]+", "");
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    //criar getset

    public Proposta toProposta(){
        return new Proposta( documento,email, nome, endereco, salario);
    }

    public String getDocumento() {
        return documento;
    }

}
