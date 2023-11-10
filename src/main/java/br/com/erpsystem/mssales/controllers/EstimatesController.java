package br.com.erpsystem.mssales.controllers;

import br.com.erpsystem.mssales.dto.EstimateDTO;
import br.com.erpsystem.mssales.dto.http.request.CreateEstimateRequestDTO;
import br.com.erpsystem.mssales.dto.http.response.*;
import br.com.erpsystem.mssales.services.EstimateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("api/v1/estimates")
@RequiredArgsConstructor
public class EstimatesController {

    private final EstimateService estimateService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateEstimateResponseDTO> createEstimate(@RequestBody CreateEstimateRequestDTO createEstimateRequestDTO){
        log.info("EstimateController.createEstimate - Start - EstimateDTO: {}", createEstimateRequestDTO);
        CreateEstimateResponseDTO responseDTO = estimateService.createEstimate(createEstimateRequestDTO);
        log.info("EstimateController.createEstimate - End");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping(value = "/cpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchEstimatesResponseDTO> findEstimatesByCpf(@PathVariable("cpf") String cpf){
        log.info("EstimateController.findEstimatesByCpf - Start - CPF: {}", cpf);
        SearchEstimatesResponseDTO responseDTO = estimateService.searchEstimateByCpf(cpf);
        log.info("EstimateController.findEstimatesByCpf - End");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponseDTO> confirmEstimate(@RequestBody EstimateDTO estimateDTO){
        log.info("EstimateController.confirmEstimate - Start - EstimateDTO: {}", estimateDTO);
        CreateOrderResponseDTO responseDTO = estimateService.confirmEstimate(estimateDTO);
        log.info("EstimateController.confirmEstimate - End");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }


}
