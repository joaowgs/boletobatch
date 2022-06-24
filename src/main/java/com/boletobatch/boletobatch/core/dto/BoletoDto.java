package com.boletobatch.boletobatch.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoletoDto implements Serializable {

    String correlationId;
    String type;
    Integer bank;
    DataDto data;
}
