package br.com.zupedu.propostasComNoSql.propostas;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "solicitaCartao", url = "http://localhost:8888/api")
public interface FeignClientCartao{




}

