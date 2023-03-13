package br.com.erpsystem.mscomercial.controllers;

import br.com.erpsystem.mscomercial.dto.PedidoDTO;
import br.com.erpsystem.mscomercial.services.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/pedido")
@RequiredArgsConstructor
public class ComercialController {

    private final PedidoService pedidoService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoDTO> criacaoPedido(@RequestBody PedidoDTO pedidoDTO){
        log.info("ComercialController.criacaoPedido - Start - PedidoDTO: {}", pedidoDTO);
        pedidoService.criarPedido(pedidoDTO);
        log.info("ComercialController.criacaoPedido - End");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
