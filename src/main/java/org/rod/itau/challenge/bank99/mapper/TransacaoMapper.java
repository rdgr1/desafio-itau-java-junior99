package org.rod.itau.challenge.bank99.mapper;

import org.rod.itau.challenge.bank99.dto.TransacaoDto;
import org.rod.itau.challenge.bank99.model.TransacaoModel;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {
    public TransacaoDto toDto(TransacaoModel model){
        var dto = new TransacaoDto(
                model.getValor(),
                model.getDataHora()
        );
        return dto;
    }
    public TransacaoModel toEntity(TransacaoDto dto){
        var entity = new TransacaoModel();
        entity.setValor(dto.valor());
        entity.setDataHora(dto.dataHora());
        return entity;
    }

}
