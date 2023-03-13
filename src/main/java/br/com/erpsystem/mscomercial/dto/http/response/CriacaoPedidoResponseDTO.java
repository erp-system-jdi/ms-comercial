package br.com.erpsystem.mscomercial.dto.http.response;

import br.com.erpsystem.mscomercial.dto.PedidoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriacaoPedidoResponseDTO {

    @JsonProperty("pedido")
    private PedidoDTO pedidoDTO;
}
