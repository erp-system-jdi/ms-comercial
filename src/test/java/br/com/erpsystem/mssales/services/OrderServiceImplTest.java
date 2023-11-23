package br.com.erpsystem.mssales.services;

import br.com.erpsystem.mssales.client.CustomerClient;
import br.com.erpsystem.mssales.client.ProductClient;
import br.com.erpsystem.mssales.dto.CustomerDTO;
import br.com.erpsystem.mssales.dto.OrderDTO;
import br.com.erpsystem.mssales.dto.http.request.CreateOrderRequestDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchOrdersResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchProductResponseDTO;
import br.com.erpsystem.mssales.entity.Order;
import br.com.erpsystem.mssales.exceptions.BusinessException;
import br.com.erpsystem.mssales.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mssales.exceptions.OrderNotFoundException;
import br.com.erpsystem.mssales.mapper.OrderMapper;
import br.com.erpsystem.mssales.mapper.OrderMapperImpl;
import br.com.erpsystem.mssales.repository.OrderRepository;
import br.com.erpsystem.mssales.utils.JsonUtils;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl service;
    @Mock
    OrderRepository repository;
    @Mock
    CustomerClient customerClient;
    @Mock
    ProductClient productClient;
    @Spy
    OrderMapper mapper = new OrderMapperImpl();

    private final static String CREATE_ORDER_RESPONSE_DTO = "payloads/CreateOrderResponseDTO.json";
    private final static String CREATE_ORDER_REQUEST_DTO = "payloads/CreateOrderRequestDTO.json";
    private final static String CUSTOMER_DTO = "payloads/CustomerDTO.json";
    private static final String ORDER_JSON_PATH = "payloads/Order.json";
    private final static String SEARCH_PRODUCT_RESPONSE_DTO = "payloads/SearchProductResponseDTO.json";
    private final static  String SEARCH_ORDER_RESPONSE_DTO = "payloads/SearchOrderResponseDTO.json";
    private final static  String SEARCH_ORDERS_RESPONSE_DTO = "payloads/SearchOrdersResponseDTO.json";


    @Test
    void createOrder() throws IOException {

        CreateOrderResponseDTO RESPONSE = JsonUtils.getObjectFromFile(CREATE_ORDER_RESPONSE_DTO, CreateOrderResponseDTO.class);
        CreateOrderRequestDTO REQUEST = JsonUtils.getObjectFromFile(CREATE_ORDER_REQUEST_DTO, CreateOrderRequestDTO.class);
        CustomerDTO CUSTOMERDTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO, CustomerDTO.class);
        Order ORDER = JsonUtils.getObjectFromFile(ORDER_JSON_PATH, Order.class);
        SearchProductResponseDTO PRODUCT_DTO = JsonUtils.getObjectFromFile(SEARCH_PRODUCT_RESPONSE_DTO, SearchProductResponseDTO.class);

        Mockito.when(customerClient.findCustomerByCpf(Mockito.any())).thenReturn(CUSTOMERDTO);
        Mockito.when(productClient.findProductById(Mockito.any())).thenReturn(PRODUCT_DTO);
        Mockito.when(repository.save(Mockito.any())).thenReturn(ORDER);

        CreateOrderResponseDTO responseDTO = service.createOrder(REQUEST);

        Assertions.assertEquals(RESPONSE, responseDTO);
    }
    @Test
    void createOrderWithCustomerNotFound() throws IOException {

        CreateOrderRequestDTO REQUEST = JsonUtils.getObjectFromFile(CREATE_ORDER_REQUEST_DTO, CreateOrderRequestDTO.class);

        Mockito.when(customerClient.findCustomerByCpf(Mockito.any())).thenReturn(null);

        Assertions.assertThrows(CustomerNotFoundException.class, () -> service.createOrder(REQUEST));
    }
    @Test
    void searchOrderById() throws IOException {

        UUID ID = UUID.fromString("3d71e7c3-7b3f-4a46-ae6d-b8fd3e7fe211");
        Order ORDER = JsonUtils.getObjectFromFile(ORDER_JSON_PATH, Order.class);
        SearchOrderResponseDTO RESPONSE = JsonUtils.getObjectFromFile(SEARCH_ORDER_RESPONSE_DTO, SearchOrderResponseDTO.class);

        Mockito.when(repository.getReferenceById(Mockito.any())).thenReturn(ORDER);

        SearchOrderResponseDTO responseDTO = service.searchOrderById(String.valueOf(ID));

        Assertions.assertEquals(RESPONSE, responseDTO);
    }

    @Test
    void searchOrderByIdWithIdNotFound() throws IOException {

        UUID ID = UUID.fromString("3d71e7c3-7b3f-4a46-ae6d-b8fd3e7fe211");

        Mockito.when(repository.getReferenceById(Mockito.any())).thenReturn(null);

        Assertions.assertThrows(BusinessException.class, () -> service.searchOrderById(String.valueOf(ID)));
    }
    @Test
    void searchOrderByCpf() throws IOException {

        String CPF = "78945612300";
        Order ORDER = JsonUtils.getObjectFromFile(ORDER_JSON_PATH, Order.class);
        SearchOrdersResponseDTO RESPONSE = JsonUtils.getObjectFromFile(SEARCH_ORDERS_RESPONSE_DTO, SearchOrdersResponseDTO.class);

        Mockito.when(repository.findOrdersBycustomerCpf(Mockito.any())).thenReturn(List.of(ORDER));

        SearchOrdersResponseDTO responseDTOS = service.searchOrderByCpf(CPF);

        Assertions.assertEquals(RESPONSE, responseDTOS);
    }

    @Test
    void searchOrderByCpfWithOrderNotFound(){

        String CPF = "78945612300";

        Mockito.when(repository.findOrdersBycustomerCpf(Mockito.any())).thenReturn(List.of());

        Assertions.assertThrows(OrderNotFoundException.class, () -> service.searchOrderByCpf(CPF));
    }



}