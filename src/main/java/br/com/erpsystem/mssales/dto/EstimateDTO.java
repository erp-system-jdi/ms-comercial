package br.com.erpsystem.mssales.dto;

import br.com.erpsystem.mssales.entity.EstimateItem;
import br.com.erpsystem.mssales.enums.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

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
    @JsonProperty("products")
    private Set<EstimateItemDTO> products;
    @JsonProperty("payment_type")
    private PaymentTypeEnum paymentType;
    @JsonProperty("expiration_date")
    private LocalDate expirationDate;
    @JsonProperty("delivery_date")
    private LocalDate deliveryDate;
    @JsonProperty("matriculation")
    private String matriculation;
}
