package com.boletobatch.boletobatch.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@Slf4j
@RequiredArgsConstructor
public class GeracaoBoletoJobConfig {

	private final JobBuilderFactory jobBuilderFactory;

	@Autowired
	private ApplicationContext context;

	@Bean
	public Job geracaoBoletosJob(@Qualifier("geracaoBoletosStep") Step geracaoBoletosStep,
				 JobExecutionListener jobExecutionListener) {
		return jobBuilderFactory
				.get("geracaoBoletosJob")
				.listener(jobExecutionListener)
				.start(geracaoBoletosStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}

	@Bean
	public JobExecutionListener jobExecutionListener() {

		return new JobExecutionListener() {
			@Override
			public void beforeJob(JobExecution jobExecution) {
				log.info("Job {} STARTED!!!", jobExecution.getJobInstance().getJobName());
			}

			@Override
			public void afterJob(JobExecution jobExecution) {
				log.info("Job {} finished with Status {}!!!", jobExecution.getJobInstance().getJobName(),
						jobExecution.getExitStatus().getExitCode());
				forceExit();
			}
		};
	}

	private void forceExit() {
		SpringApplication.exit(context, () -> 0);
		System.exit(0);
	}

}
