package br.com.erpsystem.mscomercial.dataprovider.repository;


import br.com.erpsystem.mscomercial.core.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteEntity, UUID> {
}
