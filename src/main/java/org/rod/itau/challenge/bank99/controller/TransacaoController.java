package org.rod.itau.challenge.bank99.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.rod.itau.challenge.bank99.dto.TransacaoDto;
import org.rod.itau.challenge.bank99.mapper.TransacaoMapper;
import org.rod.itau.challenge.bank99.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {
        private final TransacaoService service;
        private final TransacaoMapper mapper;

    public TransacaoController(TransacaoService service, TransacaoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @PostMapping
    @Operation(summary= "Criar uma Transação")
    @ApiResponse(responseCode = "200", description = "A transação foi aceita (ou seja foi validada, está válida e foi registrada)")
    @ApiResponse(responseCode = "422", description = "A transação não foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que 0)")
    @ApiResponse(responseCode = "400", description = "A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido)")
    public ResponseEntity<TransacaoDto> create(@RequestBody @Valid TransacaoDto dto){
            try {
                 service.createOrUpdate(mapper.toEntity(dto));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.unprocessableEntity().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
