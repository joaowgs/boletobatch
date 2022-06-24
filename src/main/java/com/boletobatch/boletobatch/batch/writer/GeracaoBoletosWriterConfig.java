package com.boletobatch.boletobatch.batch.writer;

import com.boletobatch.boletobatch.core.dto.BoletoDto;
import com.boletobatch.boletobatch.core.utils.MDCUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Configuration("geracaoBoletosWriterConfig")
@RequiredArgsConstructor
public class GeracaoBoletosWriterConfig implements ItemWriter<BoletoDto>{

	@Value("${spring.kafka.producer.topics.geracao-boleto-pdf}")
	private String topicoGeracaoBoletoPdf;

	private final KafkaTemplate<String, Serializable> jsonKafkaTemplate;

	private void enviarTopico(BoletoDto boletoDto) {
		try {
			log.info("Enviando Cobranca para o tópico {}: {}", topicoGeracaoBoletoPdf, boletoDto);
			jsonKafkaTemplate.send(topicoGeracaoBoletoPdf, boletoDto);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public void write(List<? extends BoletoDto> items) {
		items.forEach(item -> {
			MDCUtil.putCorrelationId(item.getCorrelationId().toString());
			enviarTopico(item);
			MDCUtil.removeCorrelationId();
		});
	}
}
