package org.rod.itau.challenge.bank99.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDto(
        @NotNull
        BigDecimal valor,
        @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
        LocalDateTime dataHora
) {
}
