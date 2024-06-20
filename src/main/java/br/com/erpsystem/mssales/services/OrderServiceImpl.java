package br.com.erpsystem.mssales.services;

import br.com.erpsystem.mssales.client.CustomerClient;
import br.com.erpsystem.mssales.client.ProductClient;
import br.com.erpsystem.mssales.constants.ErrorCodes;
import br.com.erpsystem.mssales.dto.CustomerDTO;
import br.com.erpsystem.mssales.dto.OrderDTO;
import br.com.erpsystem.mssales.dto.ProductDTO;
import br.com.erpsystem.mssales.dto.http.request.CreateOrderRequestDTO;
import br.com.erpsystem.mssales.dto.http.request.OrderUpdateRequestDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.OrderUpdateResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchOrdersResponseDTO;
import br.com.erpsystem.mssales.entity.Order;
import br.com.erpsystem.mssales.enums.OrderStatusEnum;
import br.com.erpsystem.mssales.exceptions.*;
import br.com.erpsystem.mssales.mapper.OrderMapper;
import br.com.erpsystem.mssales.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static br.com.erpsystem.mssales.constants.BusinessErrorConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;

    @Override
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO orderDTO) {

        log.info("OrderServiceImpl.createOrder - Start - order: {}", orderDTO);

        CustomerDTO customerDTO = findCustomer(orderDTO);

        if(orderDTO.getOrderDTO().getProducts().isEmpty() || ObjectUtils.isEmpty(customerDTO)){
            log.info("OrderServiceImpl.createOrder - Error - order: {}", orderDTO);
            throw new CustomerNotFoundException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, CUSTOMER_NOT_REGISTERED ));
        }
        orderDTO.getOrderDTO().setCreateDate(LocalDate.now());
        orderDTO.getOrderDTO().setTotalPrice(calculateTotalPrice(orderDTO));
        orderDTO.getOrderDTO().setStatus(OrderStatusEnum.AGUARDANDO_APROVACAO);
        Order order = orderRepository.save(mapper.orderDTOToOrder(orderDTO.getOrderDTO()));

        log.info("OrderServiceImpl.createOrder - End");
        return CreateOrderResponseDTO.builder().orderId(mapper.orderToOrderDTO(order).getId()).build();
    }

    @Override
    @Transactional
    public SearchOrderResponseDTO searchOrderById(String id) {
        log.info("OrderServiceImpl.searchOrderById - Start - Id: {}", id);

        OrderDTO orderDTO = mapper.orderToOrderDTO(orderRepository.getReferenceById(UUID.fromString(id)));

        if(orderDTO == null){
            log.error("OrderServiceImpl.searchOrderById - Error - Não foram encontrados pedidos com este ID!");
            throw new BusinessException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, ID_NOT_FOUND));
        }
        log.info("OrderServiceImpl.searchOrderById - End");
        return SearchOrderResponseDTO.builder().orderDTO(orderDTO).build();
    }

    @Override
    @Transactional
    public SearchOrdersResponseDTO searchOrderByCpf(String cpf) {
        log.info("OrderServiceImpl.searchOrderByCpf - Start - Cpf: {}", cpf);
        List<OrderDTO> orderDTOS = mapper.ordersToOrdersDTO(orderRepository.findOrdersBycustomerCpf(cpf));

        if(orderDTOS.isEmpty()){
            log.error("OrderServiceImpl.searchOrderByCpf - Error - Não foram encontrados pedidos para este cpf!");
            throw new OrderNotFoundException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, ORDER_NOT_FOUND));
        }
        log.info("OrderServiceImpl.searchOrderByCpf - End");
        return SearchOrdersResponseDTO.builder().orderDTO(orderDTOS).build();
    }

    @Override
    public OrderUpdateResponseDTO updateOrder(OrderUpdateRequestDTO orderUpdateRequestDTO) {
        log.info("OrderServiceImpl.updateOrder - Start - OrderUpdateRequest: {}", orderUpdateRequestDTO);

        OrderDTO orderDTO = mapper.orderToOrderDTO(orderRepository.getReferenceById(orderUpdateRequestDTO.getId()));

        //orderDTO.



        // primewiro procurar a ordem no banco
        //verificar se ela está em produção, nesse caso pode alterar endereço

        //se ainda tiver aguardando pagamento pode mudar forma de pagamento

        //depois dessas validações fazer as alterações

        //checar a lista de productsToRemove e verificar se deve excluir todos ou só alguns itens. Por exemplo tem
        // 10 produtos ABC, ai o cliente quer 5, excluir apenas 5 e deixar o restante
        return null;
    }

    private CustomerDTO findCustomer(CreateOrderRequestDTO orderRequestDTO){
        return customerClient.findCustomerByCpf(orderRequestDTO.getOrderDTO().getCustomerCpf());
    }

    private BigDecimal calculateTotalPrice(CreateOrderRequestDTO orderRequestDTO){
        getUnitPrice(orderRequestDTO);
        double sum = orderRequestDTO.getOrderDTO().getProducts().stream()
                .mapToDouble(orderItemDTO -> orderItemDTO.getUnitPrice().doubleValue() * orderItemDTO.getQuantity()).sum();
        return BigDecimal.valueOf(sum);
    }

    private void getUnitPrice(CreateOrderRequestDTO orderRequestDTO){
        orderRequestDTO.getOrderDTO().getProducts().forEach(orderItemDTO -> {
            ProductDTO productDTO = productClient.findProductById(orderItemDTO.getProductId()).getProductDTO();

            if(validateProductStock(productDTO, orderItemDTO.getQuantity())){
                orderItemDTO.setUnitPrice(productDTO.getUnitPrice());
            }else{
                log.error("OrderServiceImpl.getUnitPrice - Product Out Of Stock");
                throw new ProductOutOfStockException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, PRODUCT_OUT_OF_STOCK));
            }

        });
    }

    private boolean validateProductStock(ProductDTO productDTO, Integer orderQuantity){
        return orderQuantity <= productDTO.getQuantityInStock();
    }

}
