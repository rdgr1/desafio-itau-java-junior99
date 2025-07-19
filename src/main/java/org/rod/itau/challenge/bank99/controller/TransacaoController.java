package org.rod.itau.challenge.bank99.controller;

import lombok.extern.slf4j.Slf4j;
import org.rod.itau.challenge.bank99.mapper.TransacaoMapper;
import org.rod.itau.challenge.bank99.service.TransacaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api")
public class TransacaoController {
        private final TransacaoService service;
        private final TransacaoMapper mapper;

    public TransacaoController(TransacaoService service, TransacaoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

}
