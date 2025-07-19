package org.rod.itau.challenge.bank99.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDto(
        @NotNull(message = "Valor é obrigatório.")
        @DecimalMin(value = "0.00", inclusive = true, message = "Valor não pode ser negativo.")
        BigDecimal valor,
        @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
        @NotNull(message = "Data e hora são obrigatórios.")
        @PastOrPresent(message = "Data e hora não podem estar no futuro.")
        LocalDateTime dataHora
) {
}
