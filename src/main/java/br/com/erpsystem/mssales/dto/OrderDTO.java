package br.com.erpsystem.mssales.dto;

import br.com.erpsystem.mssales.enums.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("total_price")
    private BigDecimal totalPrice;
    @JsonProperty("customer_cpf")
    private String customerCpf;
    @JsonProperty("products")
    private Set<OrderItemDTO> products;
    @JsonProperty("payment_type")
    private PaymentTypeEnum paymentType;
    @JsonIgnore
    private LocalDate createDate;
    @JsonProperty("delivery_date")
    private LocalDate deliveryDate;
    @JsonProperty("matriculation")
    private String matriculation;
}
