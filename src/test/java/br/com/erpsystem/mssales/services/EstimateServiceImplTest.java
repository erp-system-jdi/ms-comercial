package br.com.erpsystem.mssales.services;

import br.com.erpsystem.mssales.client.CustomerClient;
import br.com.erpsystem.mssales.client.ProductClient;
import br.com.erpsystem.mssales.dto.CustomerDTO;
import br.com.erpsystem.mssales.dto.EstimateDTO;
import br.com.erpsystem.mssales.dto.OrderDTO;
import br.com.erpsystem.mssales.dto.http.request.CreateEstimateRequestDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateEstimateResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchEstimatesResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchProductResponseDTO;
import br.com.erpsystem.mssales.entity.Estimate;
import br.com.erpsystem.mssales.entity.Order;
import br.com.erpsystem.mssales.exceptions.BusinessException;
import br.com.erpsystem.mssales.exceptions.CustomerNotFoundException;
import br.com.erpsystem.mssales.exceptions.EstimateNotFoundException;
import br.com.erpsystem.mssales.exceptions.ExpiredEstimateException;
import br.com.erpsystem.mssales.mapper.EstimateMapper;
import br.com.erpsystem.mssales.mapper.EstimateMapperImpl;
import br.com.erpsystem.mssales.mapper.OrderMapper;
import br.com.erpsystem.mssales.mapper.OrderMapperImpl;
import br.com.erpsystem.mssales.repository.EstimateRepository;
import br.com.erpsystem.mssales.repository.OrderRepository;
import br.com.erpsystem.mssales.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EstimateServiceImplTest {

    @InjectMocks
    EstimateServiceImpl service;
    @Mock
    ProductClient productClient;
    @Mock
    CustomerClient customerClient;
    @Mock
    EstimateRepository estimateRepository;
    @Mock
    OrderRepository orderRepository;
    @Spy
    EstimateMapper estimateMapper = new EstimateMapperImpl();
    @Spy
    OrderMapper orderMapper = new OrderMapperImpl();

    private final static String ESTIMATE_JSON_PATH = "payloads/Estimate.json";
    private final static  String ESTIMATE_DTO_JSON_PATH = "payloads/EstimateDTO.json";
    private static final String CREATE_ESTIMATE_REQUEST_JSON = "payloads/CreateEstimateRequestDTO.json";
    private static final String CUSTOMER_DTO = "payloads/CustomerDTO.json";
    private static final String ORDER_DTO = "payloads/OrderDTO.json";
    private static final String SEARCH_PRODUCT_RESPONSE_DTO = "payloads/SearchProductResponseDTO.json";
    private static final String SEARCH_ESTIMATES_RESPONSE_DTO = "payloads/SearchEstimatesResponseDTO.json";
    private static final String CREATE_ORDER_RESPONSE_DTO = "payloads/CreateOrderResponseDTO.json";
    private static final String ORDER_JSON_PATH = "payloads/Order.json";
    private static final String ESTIMATE_DTO_SEM_DELIVERY_DATE = "payloads/EstimateDTOsemDeliveryDate.json";
    private static final String ESTIMATE_DTO_EXPIRED_ESTIMATE = "payloads/EstimateDTOExpiredEstimate.json";
    @Test
    void createEstimate() throws IOException {

        Estimate ESTIMATE = JsonUtils.getObjectFromFile(ESTIMATE_JSON_PATH, Estimate.class);
        CreateEstimateRequestDTO REQUEST = JsonUtils.getObjectFromFile(CREATE_ESTIMATE_REQUEST_JSON, CreateEstimateRequestDTO.class);
        CustomerDTO CUSTOMERDTO = JsonUtils.getObjectFromFile(CUSTOMER_DTO, CustomerDTO.class);
        SearchProductResponseDTO PRODUCTDTO = JsonUtils.getObjectFromFile(SEARCH_PRODUCT_RESPONSE_DTO, SearchProductResponseDTO.class);

        Mockito.when(customerClient.findCustomerByCpf(any())).thenReturn(CUSTOMERDTO);
        Mockito.when(productClient.findProductById(any())).thenReturn(PRODUCTDTO);
        Mockito.when(estimateRepository.save(any())).thenReturn(ESTIMATE);

        CreateEstimateResponseDTO responseDTO = service.createEstimate(REQUEST);

        Assertions.assertNotNull(responseDTO);
    }
    @Test
    void createEstimateCustomerNotFound() throws IOException {

        CreateEstimateRequestDTO REQUEST = JsonUtils.getObjectFromFile(CREATE_ESTIMATE_REQUEST_JSON, CreateEstimateRequestDTO.class);

        Mockito.when(customerClient.findCustomerByCpf(any())).thenReturn(null);

        Assertions.assertThrows(CustomerNotFoundException.class, () -> service.createEstimate(REQUEST));
    }
    @Test
    void searchEstimateByCpf() throws IOException {

        Estimate ESTIMATE = JsonUtils.getObjectFromFile(ESTIMATE_JSON_PATH, Estimate.class);
        String CPF = "42946578801";
        SearchEstimatesResponseDTO RESPONSE = JsonUtils.getObjectFromFile(SEARCH_ESTIMATES_RESPONSE_DTO, SearchEstimatesResponseDTO.class);

        Mockito.when(estimateRepository.findEstimatesByCustomerCpf(CPF)).thenReturn(List.of(ESTIMATE));

        SearchEstimatesResponseDTO responseDTO = service.searchEstimateByCpf(CPF);

        Assertions.assertEquals(RESPONSE, responseDTO);

    }
    @Test
    void searchEstimateByCpfEstimateNotFound(){

        String CPF = "42946578801";

        Mockito.when(estimateRepository.findEstimatesByCustomerCpf(Mockito.any())).thenReturn(List.of());

        Assertions.assertThrows(EstimateNotFoundException.class, () -> service.searchEstimateByCpf(CPF));
    }
    @Test
    void confirmEstimate () throws IOException {

        CreateOrderResponseDTO RESPONSEDTO = JsonUtils.getObjectFromFile(CREATE_ORDER_RESPONSE_DTO, CreateOrderResponseDTO.class);
        EstimateDTO ESTIMATEDTO = JsonUtils.getObjectFromFile(ESTIMATE_DTO_JSON_PATH, EstimateDTO.class);
        //OrderDTO ORDERDTO = JsonUtils.getObjectFromFile(ORDER_DTO, OrderDTO.class);
        Order ORDER = JsonUtils.getObjectFromFile(ORDER_JSON_PATH, Order.class);

        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(ORDER);

        CreateOrderResponseDTO responseDTO = service.confirmEstimate(ESTIMATEDTO);

        Assertions.assertEquals(RESPONSEDTO, responseDTO);
    }

    @Test
    void confirmEstimateWithDeliveryDateNull () throws IOException {

        EstimateDTO ESTIMATEDTO = JsonUtils.getObjectFromFile(ESTIMATE_DTO_SEM_DELIVERY_DATE, EstimateDTO.class);

        Assertions.assertThrows(BusinessException.class, () -> service.confirmEstimate(ESTIMATEDTO));
    }

    @Test
    void confirmEstimateWithExpiredEstimate () throws IOException {

        EstimateDTO ESTIMATEDTO = JsonUtils.getObjectFromFile(ESTIMATE_DTO_EXPIRED_ESTIMATE, EstimateDTO.class);

        Assertions.assertThrows(ExpiredEstimateException.class, () -> service.confirmEstimate(ESTIMATEDTO));
    }











}