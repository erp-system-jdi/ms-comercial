package br.com.erpsystem.mssales.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDTO {

    @JsonProperty("product_id")
    private UUID id;
    @JsonProperty("product_name")
    private String name;
    @JsonProperty("sale_unit")
    private String saleUnit;
    @JsonProperty("unit_price")
    private Double unitPrice;
    @JsonProperty("active")
    private Boolean isActive;
    @JsonProperty("description")
    private String description;
    @JsonProperty("quantity_in_stock")
    private Integer quantityInStock;
}
