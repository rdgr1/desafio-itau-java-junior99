package org.rod.itau.challenge.bank99.mapper;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rod.itau.challenge.bank99.dto.TransacaoDto;
import org.rod.itau.challenge.bank99.model.TransacaoModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransacaoMapperTest {


    @Test
    void toDto() {
        var value = new BigDecimal("25.99");
        var date = LocalDateTime.now();

        var entity = new TransacaoModel();
        entity.setValor(value);
        entity.setDataHora(date);

        var mapper = new TransacaoMapper(); // instância real

        var result = mapper.toDto(entity);

        assertNotNull(result);
        assertEquals(value, result.valor());
        assertEquals(date, result.dataHora());

    }

    @Test
    void toEntity() {
        var value = new BigDecimal("25.99");
        var date = LocalDateTime.now();
        var dto = new TransacaoDto(value,date);
        var entity = new TransacaoModel();
        entity.setValor(value);
        entity.setDataHora(date);
        var mapper = new TransacaoMapper();
        var result = mapper.toEntity(dto);
        assertNotNull(result);
        assertEquals(value, result.getValor());
        assertEquals(date, result.getDataHora());
    }
}