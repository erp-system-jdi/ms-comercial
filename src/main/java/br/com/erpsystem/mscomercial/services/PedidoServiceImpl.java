package br.com.erpsystem.mscomercial.services;

import br.com.erpsystem.mscomercial.client.ClienteFeign;
import br.com.erpsystem.mscomercial.dto.ClienteDTO;
import br.com.erpsystem.mscomercial.dto.PedidoDTO;
import br.com.erpsystem.mscomercial.entity.Pedido;
import br.com.erpsystem.mscomercial.mapper.PedidoMapper;
import br.com.erpsystem.mscomercial.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteFeign clienteFeign;
    private final PedidoMapper mapper;

    @Override
    public void criarPedido(PedidoDTO pedidoDTO) {

        ClienteDTO clienteDTO = clienteFeign.buscarClientePorCpf(pedidoDTO.getCpfCliente());

        Pedido pedido = Pedido.builder()
                .clienteId(clienteDTO.getId())
                .valorTotal(pedidoDTO.getValorTotal())
                .build();

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

    }


}
