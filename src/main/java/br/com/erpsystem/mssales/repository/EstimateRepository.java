package br.com.erpsystem.mssales.repository;

import br.com.erpsystem.mssales.dto.EstimateDTO;
import br.com.erpsystem.mssales.entity.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EstimateRepository extends JpaRepository<Estimate, UUID> {

    List<Estimate> findEstimatesByCustomerCpf(String cpf);
}
