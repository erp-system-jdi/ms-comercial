package br.com.erpsystem.mscomercial.dto.http.request;

import br.com.erpsystem.mscomercial.dto.PedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriacaoPedidoRequestDTO {

    private PedidoDTO pedidoDTO;
}
