package br.com.zupedu.propostasComNoSql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableFeignClients
@SpringBootApplication
public class PropostasComNoSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostasComNoSqlApplication.class, args);
	}

}
