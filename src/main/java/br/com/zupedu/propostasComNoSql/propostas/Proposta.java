package br.com.zupedu.propostasComNoSql.propostas;

import br.com.zupedu.propostasComNoSql.validator.CPForCNPJ;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;

@Document
public class Proposta {
    @MongoId
    private String uuid = UUID.randomUUID().toString();
    @CPForCNPJ
    private String documento;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @PositiveOrZero
    private BigDecimal salario;

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
