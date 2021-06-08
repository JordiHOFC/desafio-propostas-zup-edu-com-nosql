package br.com.zupedu.propostasComNoSql.propostas;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "solicitaRestricao", url = "http://localhost:9999/api")
public interface FeignClientSolicitaRestricao {

    @PostMapping(value = "/solicitacao", consumes = "application/json")
   void restricao(FeigClientSolicitaRestricaoRequest request);


    public static class FeigClientSolicitaRestricaoRequest {
        private String documento;
        private String nome;
        private String idProposta;

        public FeigClientSolicitaRestricaoRequest(Proposta proposta) {
            this.documento = proposta.getDocumento();
            this.nome = proposta.getNome();
            this.idProposta = proposta.getUuid();
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

        public String getIdProposta() {
            return idProposta;
        }
    }

    public static class FeigClientSolicitaRestricaoResponse{
        private String documento;
        private String nome;
        private restricao resultadoSolicitacao;
        private String idProposta;

        public FeigClientSolicitaRestricaoResponse(String documento, String nome, restricao resultadoSolicitacao, String idProposta) {
            this.documento = documento;
            this.nome = nome;
            this.resultadoSolicitacao = resultadoSolicitacao;
            this.idProposta = idProposta;
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

        public restricao getResultadoSolicitacao() {
            return resultadoSolicitacao;
        }

        public String getIdProposta() {
            return idProposta;
        }
    }

    enum restricao{
        COM_RESTRICAO, SEM_RESTRICAO
    }

}