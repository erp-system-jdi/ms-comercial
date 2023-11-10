package br.com.erpsystem.mssales.dto.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEstimateResponseDTO {
    @JsonProperty("estimate_id")
    private UUID estimateId;

}
