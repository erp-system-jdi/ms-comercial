package br.com.erpsystem.mssales.dto;

import br.com.erpsystem.mssales.entity.EstimateItem;
import br.com.erpsystem.mssales.enums.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EstimateDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("total_price")
    private BigDecimal totalPrice;
    @JsonProperty("customer_cpf")
    private String customerCpf;
    @JsonProperty("productsEstimate")
    private Set<EstimateItemDTO> productsEstimate;
    @JsonProperty("paymentType")
    private PaymentTypeEnum paymentType;
    @JsonProperty("validate_estimate")
    private LocalDate validateEstimate;
    @JsonProperty("matriculation")
    private String matriculation;
}
