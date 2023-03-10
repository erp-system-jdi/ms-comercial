package br.com.erpsystem.mscomercial.dataprovider;

import br.com.erpsystem.mscomercial.core.entities.ClienteEntity;
import br.com.erpsystem.mscomercial.core.usecase.gateway.ClienteGateway;
import br.com.erpsystem.mscomercial.dataprovider.mapper.ClienteMapper;
import br.com.erpsystem.mscomercial.dataprovider.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteDataprovider implements ClienteGateway {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper mapper;

    @Override
    public ClienteEntity buscarClientePorId(UUID id) {
        return null;
    }
}
