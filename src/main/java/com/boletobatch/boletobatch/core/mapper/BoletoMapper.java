package com.boletobatch.boletobatch.core.mapper;

import com.boletobatch.boletobatch.core.dto.BoletoDto;
import com.boletobatch.boletobatch.core.dto.CobrancaDto;
import com.boletobatch.boletobatch.core.dto.DataDto;

import java.util.UUID;

public interface BoletoMapper {

    static BoletoDto mapCobrancaToBoleto(CobrancaDto cobrancaDto) {
        return BoletoDto.builder()
                .correlationId(UUID.randomUUID().toString())
                .bank(cobrancaDto.getBanco())
                .type("pdf")
                .data(mapCobrancaToData(cobrancaDto))
                .build();
    }

    static DataDto mapCobrancaToData(CobrancaDto cobrancaDto) {
        return DataDto.builder()
                .conta_corrente(cobrancaDto.getContaCorrente())
                .documento_cedente(cobrancaDto.getDocumentoCedente())
                .sacado_documento(cobrancaDto.getSacadoDocumento())
                .valor(cobrancaDto.getValor())
                .agencia(cobrancaDto.getAgencia())
                .convenio(cobrancaDto.getConvenio())
                .sacado(cobrancaDto.getSacado())
                .cedente(cobrancaDto.getCedente())
                .carteira(cobrancaDto.getCarteira())
                .build();
    }
}
