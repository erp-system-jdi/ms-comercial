package br.com.erpsystem.mssales.dto.http.request;

import br.com.erpsystem.mssales.dto.EstimateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEstimateRequestDTO {
    @JsonProperty("estimate")
    private EstimateDTO estimateDTO;
}
