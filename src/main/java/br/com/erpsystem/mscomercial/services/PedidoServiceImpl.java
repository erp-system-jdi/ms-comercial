package br.com.erpsystem.mscomercial.services;

import br.com.erpsystem.mscomercial.client.ClienteFeign;
import br.com.erpsystem.mscomercial.dto.ClienteDTO;
import br.com.erpsystem.mscomercial.dto.PedidoDTO;
import br.com.erpsystem.mscomercial.entity.Pedido;
import br.com.erpsystem.mscomercial.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mscomercial.exceptions.ExceptionsConstants;
import br.com.erpsystem.mscomercial.mapper.PedidoMapper;
import br.com.erpsystem.mscomercial.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteFeign clienteFeign;
    private final PedidoMapper mapper;

    @Override
    public void criarPedido(PedidoDTO pedidoDTO) {

        log.info("PedidoServiceImpl.criarPedido - Start - PedidoDTO: {}", pedidoDTO);

        Optional<ClienteDTO> clienteDTO = clienteFeign.buscarClientePorCpf(pedidoDTO.getCliente().getCpf());

        if(clienteDTO.isEmpty()){
            log.info("PedidoServiceImpl.criarPedido - Client Not Found - PedidoDTO: {}", pedidoDTO);
            throw new CustomerNotFoundException(ExceptionsConstants.CLIENT_NOT_FOUND_ORDER);
        }

        Pedido pedido = Pedido.builder()
                .clienteId(clienteDTO.get().getId())
                .valorTotal(pedidoDTO.getValorTotal())
                .build();

        pedidoRepository.save(pedido);

        log.info("PedidoServiceImpl.criarPedido - Pedido Criado - End");
    }


}
