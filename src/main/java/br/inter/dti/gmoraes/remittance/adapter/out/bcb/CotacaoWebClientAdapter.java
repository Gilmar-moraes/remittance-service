package br.inter.dti.gmoraes.remittance.adapter.out.bcb;

import br.inter.dti.gmoraes.remittance.adapter.out.bcb.dto.CotacaoApiResponse;
import br.inter.dti.gmoraes.remittance.adapter.out.bcb.dto.CotacaoResponse;
import br.inter.dti.gmoraes.remittance.application.port.out.CotacaoClientPort;
import br.inter.dti.gmoraes.remittance.domain.exception.RegraNegocioException;
import br.inter.dti.gmoraes.remittance.domain.model.Cotacao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

@Component
public class CotacaoWebClientAdapter implements CotacaoClientPort {
    private final WebClient webClient;

    public CotacaoWebClientAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @Cacheable(value = "cotacoes", key = "#data")
    public Cotacao buscarCotacao(LocalDate data) {

        LocalDate dataConsulta = obterDataUtil(data);

        String dataFormatada = dataConsulta.format(
                DateTimeFormatter.ofPattern("MM-dd-yyyy")
        );

        CotacaoApiResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/CotacaoDolarDia(dataCotacao=@dataCotacao)")
                        .queryParam("@dataCotacao", "'" + dataFormatada + "'")
                        .queryParam("$format", "json")
                        .build())
                .retrieve()
                .bodyToMono(CotacaoApiResponse.class)
                .block();

        if (response == null || response.value().isEmpty()) {
            throw new RegraNegocioException("Cotação não encontrada para a data informada.");
        }

        CotacaoResponse dto = response.value().getFirst();

        return new Cotacao(dto.cotacaoCompra(),
                LocalDateTime.parse(dto.dataHoraCotacao(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                )
        );
    }

    private LocalDate obterDataUtil(LocalDate data) {

        if (data.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return data.minusDays(1);
        }

        if (data.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return data.minusDays(2);
        }
        return data;
    }
}
