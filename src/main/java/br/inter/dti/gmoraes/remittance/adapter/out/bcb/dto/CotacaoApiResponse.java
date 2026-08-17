package br.inter.dti.gmoraes.remittance.adapter.out.bcb.dto;

import java.util.List;

public record CotacaoApiResponse(

        List<CotacaoResponse> value

) {
}
