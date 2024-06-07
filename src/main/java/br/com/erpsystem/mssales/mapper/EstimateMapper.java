package br.com.erpsystem.mssales.mapper;

import br.com.erpsystem.mssales.dto.EstimateDTO;
import br.com.erpsystem.mssales.entity.Estimate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstimateMapper {

    Estimate estimateDTOToEstimate(EstimateDTO estimateDTO);

    EstimateDTO estimateToEstimateDTO(Estimate estimate);

    List<Estimate> estimateDTOToEstimates(List<EstimateDTO> estimateDTOS);

    List<EstimateDTO> estimatesToEstimatesDTO(List<Estimate> estimatesDTOS);
}
