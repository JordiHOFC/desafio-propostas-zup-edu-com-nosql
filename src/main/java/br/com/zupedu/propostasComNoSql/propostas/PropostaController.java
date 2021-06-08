package br.com.zupedu.propostasComNoSql.propostas;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final PropostaRepository repository;
    private final FeignClientSolicitaRestricao solicitaRestricao;

    public PropostaController(PropostaRepository repository, FeignClientSolicitaRestricao solicitaRestricao) {
        this.repository = repository;
        this.solicitaRestricao = solicitaRestricao;
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaRequest request) {

        if (repository.existsByDocumento(request.getDocumento())) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Proposta proposta = repository.insert(request.toProposta());

        try {
            solicitaRestricao.restricao(new FeignClientSolicitaRestricao.FeigClientSolicitaRestricaoRequest(proposta));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
//        proposta.associarCartao(cartao);

        URI location = UriComponentsBuilder.fromUriString("http://localhost/propostas/{id}").port(8082).buildAndExpand(proposta.getUuid()).toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> busca(@PathParam("id") String id) {
        Optional<Proposta> proposta = repository.findById(id);
        return (proposta.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(proposta.get());
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Proposta> propostas = repository.findAll();
        return  ResponseEntity.ok(propostas);
    }




}
