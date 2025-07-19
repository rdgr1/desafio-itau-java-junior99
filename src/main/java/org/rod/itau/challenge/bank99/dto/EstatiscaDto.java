package org.rod.itau.challenge.bank99.dto;

import java.math.BigDecimal;
import java.util.BitSet;

public record EstatiscaDto(
        Integer count,
        BigDecimal sum,
        BigDecimal avg,
        BigDecimal min,
        BigDecimal max
){
}
