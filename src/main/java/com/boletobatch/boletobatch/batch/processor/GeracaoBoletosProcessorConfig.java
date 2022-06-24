package com.boletobatch.boletobatch.batch.processor;

import com.boletobatch.boletobatch.core.dto.BoletoDto;
import com.boletobatch.boletobatch.core.dto.CobrancaDto;
import com.boletobatch.boletobatch.core.mapper.BoletoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class GeracaoBoletosProcessorConfig implements ItemProcessor<CobrancaDto, BoletoDto> {

    @Override
    public BoletoDto process(CobrancaDto cobrancaDto) throws Exception {
        try {
            log.info("Processando cobranca: {}", cobrancaDto);
            return BoletoMapper.mapCobrancaToBoleto(cobrancaDto);

        } catch (Exception e) {
            log.error("Cobranca com problema de importação.");
        }
        return null;
    }
}
