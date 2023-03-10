package br.com.erpsystem.mscomercial.core.usecase.gateway;

import br.com.erpsystem.mscomercial.core.entities.ClienteEntity;

import java.util.UUID;

public interface ClienteGateway {

    ClienteEntity buscarClientePorId(UUID id);
}
