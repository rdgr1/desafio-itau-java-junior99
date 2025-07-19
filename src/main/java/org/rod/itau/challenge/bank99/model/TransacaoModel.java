package org.rod.itau.challenge.bank99.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacao")
@Data
@EqualsAndHashCode
public class TransacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private BigDecimal valor;
    private LocalDateTime dataHora;
}
