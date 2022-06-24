package com.boletobatch.boletobatch.batch.step;

import com.boletobatch.boletobatch.core.dto.BoletoDto;
import com.boletobatch.boletobatch.core.dto.CobrancaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class GeracaoBoletosStepConfig {
	@Value("${batch.chunkSize}")
	private int CHUNK_SIZE;

	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step geracaoBoletosStep(
			ItemReader<CobrancaDto> reader,
			ItemProcessor<CobrancaDto, BoletoDto> processor,
			@Qualifier("geracaoBoletosWriterConfig") ItemWriter<BoletoDto> writer
			/*@Qualifier("mfTransactionManager") PlatformTransactionManager transactionManager*/) {
		return stepBuilderFactory
				.get("geracaoBoletosStep")
				.<CobrancaDto, BoletoDto>chunk(CHUNK_SIZE)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.startLimit(1)
				.build();
	}

}
