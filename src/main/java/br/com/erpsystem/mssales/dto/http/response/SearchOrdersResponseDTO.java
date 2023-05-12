package br.com.erpsystem.mssales.dto.http.response;

import br.com.erpsystem.mssales.dto.OrderDTO;
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
public class SearchOrdersResponseDTO {

    @JsonProperty("orders")
    private List<OrderDTO> orderDTO;
}
