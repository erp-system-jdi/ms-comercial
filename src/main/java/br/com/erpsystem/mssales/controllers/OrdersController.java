package br.com.erpsystem.mssales.controllers;

import br.com.erpsystem.mssales.dto.http.request.CreateOrderRequestDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchOrdersResponseDTO;
import br.com.erpsystem.mssales.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@RequestBody @Validated CreateOrderRequestDTO createOrderRequestDTO){
        log.info("ComercialController.criacaoPedido - Start - OrderDTO: {}", createOrderRequestDTO);
        CreateOrderResponseDTO responseDTO = orderService.createOrder(createOrderRequestDTO);
        log.info("ComercialController.criacaoPedido - End");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping(value = "/id/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchOrderResponseDTO> findOrderById(@PathVariable("id") String id){
        log.info("ComercialController.findOrderById - Start - Id: {}", id);
        SearchOrderResponseDTO responseDTO = orderService.searchOrderById(id);
        log.info("ComercialController.findOrderById - End");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping(value = "/cpf/{cpf}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchOrdersResponseDTO> findOrdersByCpf(@PathVariable("cpf") String cpf){
        log.info("ComercialController.findOrdersByCpf - Start - CPF: {}", cpf);
        SearchOrdersResponseDTO responseDTO = orderService.searchOrderByCpf(cpf);
        log.info("ComercialController.findOrdersByCpf - End");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

}
