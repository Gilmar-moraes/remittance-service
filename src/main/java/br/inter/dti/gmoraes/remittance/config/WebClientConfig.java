package br.inter.dti.gmoraes.remittance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata")
                .build();
    }

}
