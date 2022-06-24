package com.boletobatch.boletobatch.batch.reader.mapper;

import com.boletobatch.boletobatch.core.dto.CobrancaDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CobrancaMapper implements RowMapper<CobrancaDto> {

    @Override
    public CobrancaDto mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        CobrancaDto cobrancaDto = new CobrancaDto();
        cobrancaDto.setAgencia(rs.getString("numero_agencia"));
        cobrancaDto.setBanco(rs.getInt("codigo"));
        cobrancaDto.setCarteira(rs.getString("carteira"));
        cobrancaDto.setCedente(rs.getString("nome_fantasia"));
        cobrancaDto.setDocumentoCedente(rs.getString("cnpj"));
        cobrancaDto.setSacado(rs.getString("nome"));
        cobrancaDto.setSacadoDocumento(rs.getString("cpf"));
        cobrancaDto.setContaCorrente(rs.getString("numero_conta"));
        cobrancaDto.setConvenio(rs.getInt("convenio"));
        cobrancaDto.setValor(rs.getBigDecimal("valor"));
        cobrancaDto.setDigitoAgencia(rs.getString("digito_conta"));
        cobrancaDto.setDigitoConta(rs.getString("digito_agencia"));
        return cobrancaDto;
    }

}
