package org.rod.itau.challenge.bank99.service;

import jakarta.transaction.Transactional;
import org.rod.itau.challenge.bank99.dto.EstatiscaDto;
import org.rod.itau.challenge.bank99.dto.TransacaoDto;
import org.rod.itau.challenge.bank99.mapper.TransacaoMapper;
import org.rod.itau.challenge.bank99.model.TransacaoModel;
import org.rod.itau.challenge.bank99.repo.TransacaoRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TransacaoService {
    // Dependências
    private final TransacaoRepo repo;
    private final TransacaoMapper mapper;
    // Injetor Clássico
    public TransacaoService(TransacaoRepo repo, TransacaoMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    // Rollback em Caso de Erro
    @Transactional
    public TransacaoDto createOrUpdate(TransacaoModel transacao){
        // Valor Negativo
        if (transacao.getValor().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("O valor não pode ser negativo!");
        }
        // Data Antes ou Depois
        if (transacao.getDataHora().isAfter(LocalDateTime.now()) || transacao.getDataHora().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("A data deve estar no presente");
        }
        var saved = repo.save(transacao);
        return mapper.toDto(saved);
    }
    // Limpar Todas
    public void deleteAll(){
        var all = repo.findAll();
        repo.deleteAll(all);
    }
    // Buscar Todas
    public List<TransacaoDto> findAll(){
        var all = repo.findAll();
        List<TransacaoDto> dtos = new ArrayList<>();
        all.forEach(transacao -> { dtos.add(mapper.toDto(transacao)); });
        return dtos;
    }
    // Insights das Ultimas Transações
    public EstatiscaDto insights(){
        // Todas Transações
        var all = repo.findAll();
        var today = LocalDateTime.now();
        var seconds = today.minusSeconds(60);
        // Transações dos Ultimos 60s
        var lasts = all.stream().filter(
                transacao -> transacao.getDataHora().isAfter(seconds)
        ).toList();
        // Mapa de Valores
        var map = lasts.stream().map(TransacaoModel::getValor).toList();
        // Quantidade
        var count = lasts.size();
        // Soma
        var sum = map.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
        // Média
        var avg = map.isEmpty() ? BigDecimal.ZERO :
                sum.divide(BigDecimal.valueOf(map.size()),2, RoundingMode.HALF_UP);
        // Mínimo
        var min = map.stream().min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
        // Máximo
        var max = map.stream().max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
        return new EstatiscaDto(count,sum,avg,min,max);
    }
}
