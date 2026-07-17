package br.inter.dti.gmoraes.remittance_service;

import org.springframework.boot.SpringApplication;

public class TestRemittanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(RemittanceServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
