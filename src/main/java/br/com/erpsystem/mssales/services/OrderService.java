package br.com.erpsystem.mssales.services;


import br.com.erpsystem.mssales.dto.http.request.CreateOrderRequestDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchOrdersResponseDTO;

public interface OrderService {

    CreateOrderResponseDTO createOrder(CreateOrderRequestDTO orderDTO);
    SearchOrderResponseDTO searchOrderById(String id);
    SearchOrdersResponseDTO searchOrderByCpf(String cpf);

}
