package org.rod.itau.challenge.bank99.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rod.itau.challenge.bank99.dto.TransacaoDto;
import org.rod.itau.challenge.bank99.mapper.TransacaoMapper;
import org.rod.itau.challenge.bank99.model.TransacaoModel;
import org.rod.itau.challenge.bank99.repo.TransacaoRepo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {
    @Mock
    private TransacaoRepo repo;
    @Mock
    private TransacaoMapper mapper;
    @InjectMocks
    private TransacaoService service;

    @Test
    @DisplayName("Deve criar ou atualizar uma entidade")
    void createOrUpdate() {
        var value = new BigDecimal("124.99");
        // Dto
        var dto = new TransacaoDto(
                value,LocalDateTime.now()
        );
        // Entidade
        var entity = new TransacaoModel();
        entity.setValor(value);
        entity.setDataHora(LocalDateTime.now());
        // Retorno
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        var result = service.createOrUpdate(entity);
        // Asserts
        Assertions.assertEquals(dto,result);
        Assertions.assertNotNull(result);
        // Verificação
        verify(repo,times(1)).save(entity);
        verify(mapper,times(1)).toDto(entity);
    }

    @Test
    void deleteAll() {
        // Valor Genérico
        var value = new BigDecimal("124.99");
        // Data
        var date = LocalDateTime.now();
        // Esperado
        List<TransacaoDto> expected = new ArrayList<>();
        // 1 Objeto
        var first = new TransacaoModel();
        first.setValor(value);
        first.setDataHora(date);
        // 2 Objeto
        var second = new TransacaoModel();
        second.setValor(value);
        second.setDataHora(date);

        // Salvando
        var savedFirst = service.createOrUpdate(first);
        var savedSecond = service.createOrUpdate(second);

        // Result
        service.deleteAll();
        var result = service.findAll();

        // Assert
        assertEquals(expected,result);
        // Verificação
        verify(repo, times(1)).deleteAll();
        verify(repo,times(1)).findAll();
    }

    @Test
    void findAll() {
        // Valores
        var date = LocalDateTime.now();
        var value = new BigDecimal("124.99");
        // DTOs
        var dto1 = new TransacaoDto(value, date);
        var dto2 = new TransacaoDto(value, date);
        // Objetos
        var first = new TransacaoModel();
        first.setValor(value);
        first.setDataHora(date);

        var second = new TransacaoModel();
        second.setValor(value);
        second.setDataHora(date);

        // Mocks
        when(repo.findAll()).thenReturn(List.of(first, second));
        when(mapper.toDto(first)).thenReturn(dto1);
        when(mapper.toDto(second)).thenReturn(dto2);

        // Act
        var result = service.findAll();

        // Assert
        assertEquals(List.of(dto1, dto2), result);
        assertNotNull(result);

        // Verify
        verify(repo, times(1)).findAll();
        verify(mapper, times(2)).toDto(any(TransacaoModel.class));
    }

    @Test
    void insights_comTransacoesNosUltimos60Segundos() {
        var baseTime = LocalDateTime.of(2025, 7, 19, 20, 0, 0);

        var t1 = new TransacaoModel();
        t1.setValor(new BigDecimal("25.99"));
        t1.setDataHora(baseTime.minusSeconds(10));

        var t2 = new TransacaoModel();
        t2.setValor(new BigDecimal("55.99"));
        t2.setDataHora(baseTime.minusSeconds(30));

        var t3 = new TransacaoModel();
        t3.setValor(new BigDecimal("999.99"));
        t3.setDataHora(baseTime.minusMinutes(5)); // deve ser ignorada

        when(repo.findAll()).thenReturn(List.of(t1, t2, t3));

        try (MockedStatic<LocalDateTime> mockedNow = Mockito.mockStatic(LocalDateTime.class, CALLS_REAL_METHODS)) {
            mockedNow.when(LocalDateTime::now).thenReturn(baseTime);

            var result = service.insights();

            assertEquals(2, result.count());
            assertEquals(new BigDecimal("81.98"), result.sum());
            assertEquals(new BigDecimal("40.99"), result.avg());
            assertEquals(new BigDecimal("25.99"), result.min());
            assertEquals(new BigDecimal("55.99"), result.max());
        }
    }
}