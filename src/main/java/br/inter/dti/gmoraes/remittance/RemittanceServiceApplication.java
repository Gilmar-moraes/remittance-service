package br.inter.dti.gmoraes.remittance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RemittanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemittanceServiceApplication.class, args);
	}

}
