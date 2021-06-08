package br.com.zupedu.propostasComNoSql.propostas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
class PropostaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PropostaRepository repository;



    @BeforeEach
    public void setup(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve cadastrar uma proposta caso os dados sejam validos")
    public void cadastrarPropostas() throws Exception {
        PropostaRequest propostaRequest = new PropostaRequest("258.505.160-93", "jordi@jordi.com", "Jordi Bancario", "Rua do dinheiro numero 1000000", new BigDecimal("2500"));
        String jsonRequest = mapper.writeValueAsString(propostaRequest);
        mockMvc.perform(
                post("/propostas")
                .contentType(APPLICATION_JSON)
                .content(jsonRequest)
        ).andExpect(
                status().isCreated()
        ).andExpect(
                redirectedUrlPattern("http://localhost:8082/propostas/**")
        );
    }

    @Test
    @DisplayName("Nao deve cadastrar com email invalido")
    public void naoDeveCadastrarPorEmailInvalido() throws Exception{
        PropostaRequest propostaRequest = new PropostaRequest("258.505.160-93", "jordijordi.com", "Jordi Bancario", "Rua do dinheiro numero 1000000", new BigDecimal("2500"));
        String jsonRequest = mapper.writeValueAsString(propostaRequest);
        mockMvc.perform(
                post("/propostas")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    @DisplayName("Nao deve cadastrar com documento já cadastrado")
    public void naoDeveCadastrarPorDocumentoJaCadastrado() throws Exception{
        PropostaRequest propostaRequest = new PropostaRequest("258.505.160-93", "jordi@jordi.com", "Jordi Bancario", "Rua do dinheiro numero 1000000", new BigDecimal("2500"));
        repository.save(propostaRequest.toProposta());
        String jsonRequest = mapper.writeValueAsString(propostaRequest);
        mockMvc.perform(
                post("/propostas")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpect(
                status().isUnprocessableEntity()
        );
    }

    @Test
    @DisplayName("Nao deve cadastrar com documento invalido")
    public void naoDeveCadastrarPorDocumentoInvalido() throws Exception{
        PropostaRequest propostaRequest = new PropostaRequest("1111111", "jordi@jordi.com", "Jordi Bancario", "Rua do dinheiro numero 1000000", new BigDecimal("2500"));
        String jsonRequest = mapper.writeValueAsString(propostaRequest);
        mockMvc.perform(
                post("/propostas")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    @DisplayName("Nao deve cadastrar com endereco vazio")
    public void naoDeveCadastrarPorEnderecoVazio() throws Exception{
        PropostaRequest propostaRequest = new PropostaRequest("258.505.160-93", "jordijordi.com", "Jordi Bancario", "", new BigDecimal("2500"));
        String jsonRequest = mapper.writeValueAsString(propostaRequest);
        mockMvc.perform(
                post("/propostas")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    @DisplayName("Nao deve cadastrar com salario negativo")
    public void naoDeveCadastrarComSalarioNegativo() throws Exception{
        PropostaRequest propostaRequest = new PropostaRequest("258.505.160-93", "jordijordi.com", "Jordi Bancario", "Rua do dinheiro numero 1000000", new BigDecimal("-2500"));
        String jsonRequest = mapper.writeValueAsString(propostaRequest);
        mockMvc.perform(
                post("/propostas")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpect(
                status().isBadRequest()
        );
    }




}