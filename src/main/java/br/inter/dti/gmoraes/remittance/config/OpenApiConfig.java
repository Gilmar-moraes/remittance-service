package br.inter.dti.gmoraes.remittance.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Remittance Service")
                        .description("API para realização de remessas entre usuários PF e PJ.")
                        .version("1.0.0"));

    }

}
