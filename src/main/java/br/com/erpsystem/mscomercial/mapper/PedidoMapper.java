package br.com.erpsystem.mscomercial.mapper;

import br.com.erpsystem.mscomercial.dto.PedidoDTO;
import br.com.erpsystem.mscomercial.entity.Pedido;
import org.mapstruct.Mapper;

@Mapper
public interface PedidoMapper {

    Pedido clienteDtoToCliente(PedidoDTO clienteDTO);
    PedidoDTO clienteToClienteDTO(Pedido cliente);
}
