package br.com.erpsystem.mssales.dto.http.response;

import br.com.erpsystem.mssales.dto.OrderDTO;
import br.com.erpsystem.mssales.dto.OrderItemDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderUpdateResponseDTO {
    @JsonProperty("order")
    private OrderDTO orderDTO;
}
