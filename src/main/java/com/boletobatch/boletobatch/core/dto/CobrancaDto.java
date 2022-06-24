package com.boletobatch.boletobatch.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CobrancaDto {

    String id;
    BigDecimal valor;
    String cedente;
    String documentoCedente;
    String sacado;
    String sacadoDocumento;
    String agencia;
    String contaCorrente;
    Integer convenio;
    String carteira;
    Integer banco;
    String digitoConta;
    String digitoAgencia;
    String endereco;

}
