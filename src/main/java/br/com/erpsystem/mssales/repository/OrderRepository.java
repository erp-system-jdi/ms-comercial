package br.com.erpsystem.mssales.repository;

import br.com.erpsystem.mssales.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findOrdersBycustomerCpf(String cpf);

}
