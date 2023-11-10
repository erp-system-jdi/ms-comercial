package br.com.erpsystem.mssales.dto.http.response;

import br.com.erpsystem.mssales.dto.EstimateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchEstimatesResponseDTO {
    @JsonProperty("estimates")
    private List<EstimateDTO> estimateDTO;
}
