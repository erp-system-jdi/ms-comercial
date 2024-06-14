package br.com.erpsystem.mssales.dto.http.request;

import br.com.erpsystem.mssales.dto.OrderItemDTO;
import br.com.erpsystem.mssales.enums.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderUpdateRequestDTO {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("payment_type")
    private PaymentTypeEnum paymentType;
    @JsonProperty("products_to_remove")
    private List<OrderItemDTO> productsToRemove;
    @JsonProperty("products_to_add")
    private List<OrderItemDTO> productsToAdd;
    @JsonProperty("delivery_address")
    private String deliveryAddress;
}
