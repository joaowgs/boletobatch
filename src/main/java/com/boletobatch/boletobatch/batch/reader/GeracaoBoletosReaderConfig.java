package com.boletobatch.boletobatch.batch.reader;

import com.boletobatch.boletobatch.batch.reader.mapper.CobrancaMapper;
import com.boletobatch.boletobatch.core.dto.CobrancaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class GeracaoBoletosReaderConfig {
    private DataSource dataSource;
    private static final String SELECT_COBRANCAS = "SELECT cb.numero_conta, cb.digito as digito_conta, b.codigo, b" +
            ".carteira, a" +
            ".numero_agencia, a.digito as digito_agencia, a.convenio, e.nome_fantasia, e.cnpj, p.nome, p.cpf, c.valor ";
    private static final String FROM_TABLE = "FROM cobranca.cobranca c, cobranca.empresa e, cobranca.conta_bancaria cb, cobranca.agencia a, " +
            "cobranca.banco b, cobranca.pessoa p, cobranca.cidade ci, cobranca.estado es ";

    private static final String WHERE = "where e.id = c.empresa_id and c.conta_bancaria_id = cb.id and cb.agencia_id " +
            "= a.id and a.banco_id  = b.id and c.pessoa_id = p.id and p.cidade_id = ci.id and ci.estado_id = es.id ";

    public GeracaoBoletosReaderConfig(@Qualifier("pgDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcPagingItemReader<CobrancaDto> viewReader() {
        final JdbcPagingItemReader<CobrancaDto> reader = new JdbcPagingItemReader<>();
        final CobrancaMapper cobrancaMapper = new CobrancaMapper();

        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setPageSize(100);
        reader.setRowMapper(cobrancaMapper);
        reader.setQueryProvider(createQuery());

        return reader;
    }

    private PagingQueryProvider createQuery() {
        final Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("numero_conta", Order.ASCENDING);
        final PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause(SELECT_COBRANCAS);
        queryProvider.setFromClause(FROM_TABLE);
        queryProvider.setWhereClause(WHERE);
        queryProvider.setSortKeys(sortKeys);
        return queryProvider;
    }

}
