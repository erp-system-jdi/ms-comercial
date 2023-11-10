package br.com.erpsystem.mssales.services;

import br.com.erpsystem.mssales.dto.EstimateDTO;
import br.com.erpsystem.mssales.dto.http.request.CreateEstimateRequestDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateEstimateResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchEstimatesResponseDTO;

public interface EstimateService {

    CreateEstimateResponseDTO createEstimate(CreateEstimateRequestDTO estimateDTO);

    SearchEstimatesResponseDTO searchEstimateByCpf(String cpf);

    CreateOrderResponseDTO confirmEstimate(EstimateDTO estimateDTO);


}
