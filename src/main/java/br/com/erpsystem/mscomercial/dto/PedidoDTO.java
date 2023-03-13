package br.com.erpsystem.mscomercial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PedidoDTO {

    @JsonProperty("pedido_id")
    private UUID id;
    @JsonProperty("valor_total")
    private BigDecimal valorTotal;
    @JsonProperty("cliente")
    private ClienteDTO cliente;
}
