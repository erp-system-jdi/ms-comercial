package br.com.erpsystem.mssales.services;

import br.com.erpsystem.mssales.client.CustomerClient;
import br.com.erpsystem.mssales.client.ProductClient;
import br.com.erpsystem.mssales.constants.ErrorCodes;
import br.com.erpsystem.mssales.dto.CustomerDTO;
import br.com.erpsystem.mssales.dto.EstimateDTO;
import br.com.erpsystem.mssales.dto.OrderDTO;
import br.com.erpsystem.mssales.dto.ProductDTO;
import br.com.erpsystem.mssales.dto.http.request.CreateEstimateRequestDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateEstimateResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.CreateOrderResponseDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchEstimatesResponseDTO;
import br.com.erpsystem.mssales.entity.Estimate;
import br.com.erpsystem.mssales.entity.Order;
import br.com.erpsystem.mssales.exceptions.*;
import br.com.erpsystem.mssales.mapper.EstimateMapper;
import br.com.erpsystem.mssales.mapper.OrderMapper;
import br.com.erpsystem.mssales.repository.EstimateRepository;
import br.com.erpsystem.mssales.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static br.com.erpsystem.mssales.constants.BusinessErrorConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstimateServiceImpl implements EstimateService{

    private final ProductClient productClient;
    private final CustomerClient customerClient;
    private final EstimateRepository estimateRepository;
    private final OrderRepository orderRepository;
    private final EstimateMapper estimateMapper;

    private final OrderMapper orderMapper;

    @Override
    public CreateEstimateResponseDTO createEstimate(CreateEstimateRequestDTO estimateDTO) {

        log.info("EstimateServiceImpl.createEstimate - Start - estimate: {}", estimateDTO);

        CustomerDTO customerDTO = findCustomer(estimateDTO);

        log.info("EstimateServiceImpl.createEstimate - Customer: {}", customerDTO);

        if(estimateDTO.getEstimateDTO().getProducts().isEmpty() || ObjectUtils.isEmpty(customerDTO)){
            log.info("EstimateServiceImpl.createEstimate - Error - estimate: {}", estimateDTO);
            throw new CustomerNotFoundException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, CUSTOMER_NOT_REGISTERED));
        }

        LocalDate validateEstimate = LocalDate.now().plusDays(5);
        estimateDTO.getEstimateDTO().setExpirationDate(validateEstimate);
        estimateDTO.getEstimateDTO().setTotalPrice(calculateTotalPrice(estimateDTO));
        Estimate estimate = estimateRepository.save(estimateMapper.estimateDTOToEstimate(estimateDTO.getEstimateDTO()));
        return CreateEstimateResponseDTO.builder().estimateId(estimateMapper.estimateToEstimateDTO(estimate).getId()).build();
    }

    @Override
    @Transactional
    public SearchEstimatesResponseDTO searchEstimateByCpf(String cpf) {
        log.info("EstimateServiceImpl.searchEstimateByCpf - Start - Cpf: {}", cpf);

        List<EstimateDTO> estimateDTO = estimateMapper.estimatesToEstimatesDTO(estimateRepository.findEstimatesByCustomerCpf(cpf));

        if(estimateDTO.isEmpty()){
            log.error("EstimateServiceImpl.searchEstimateByCpf - Error - Não foram encontrados orçamentos para este cliente!");
            throw new EstimateNotFoundException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, ESTIMATE_NOT_FOUND));
        }
        log.info("EstimateServiceImpl.searchEstimateByCpf - End");
        return SearchEstimatesResponseDTO.builder().estimateDTO(estimateDTO).build();
    }

    @Override
    public CreateOrderResponseDTO confirmEstimate(EstimateDTO estimateDTO) {

        if(!LocalDate.now().isAfter(estimateDTO.getExpirationDate())){
            log.info("EstimateServiceImpl.confirmEstimate - O orçamento está válido! (D-5)");

            if(estimateDTO.getDeliveryDate() == null){
                log.info("EstimateServiceImpl.confirmEstimate - Error - O preenchimento da data de entrega é obrigatório!");
                throw new BusinessException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, DELIVERY_DATE));
            }

            OrderDTO orderDTO = orderMapper.estimateDTOToOrderDTO(estimateDTO);
            orderDTO.setCreateDate(LocalDate.now());
            Order order = orderRepository.save(orderMapper.orderDTOToOrder(orderDTO));

            log.info("EstimateServiceImpl.confirmEstimate - Orçamento aprovado, pedido realizado! Order: {} ", order.getId());
            return CreateOrderResponseDTO.builder().orderId(orderMapper.orderToOrderDTO(order).getId()).build();

        }else{
            log.error("O orçamento está vencido. Por favor solicite um novo orçamento!");
            throw new ExpiredEstimateException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, EXPIRED_ESTIMATE));
        }
    }

    private CustomerDTO findCustomer(CreateEstimateRequestDTO estimateRequestDTO){
        return customerClient.findCustomerByCpf(estimateRequestDTO.getEstimateDTO().getCustomerCpf());

    }

    private BigDecimal calculateTotalPrice(CreateEstimateRequestDTO estimateRequestDTO){
        getUnitPrice(estimateRequestDTO);
        double sum = estimateRequestDTO.getEstimateDTO().getProducts().stream()
                .mapToDouble(estimateItemDTO -> estimateItemDTO.getUnitPrice().doubleValue() * estimateItemDTO.getQuantity()).sum();

        return BigDecimal.valueOf(sum);
    }

    private void getUnitPrice(CreateEstimateRequestDTO estimateRequestDTO){
        estimateRequestDTO.getEstimateDTO().getProducts().forEach(estimateItem -> {
            ProductDTO productDTO = productClient.findProductById(estimateItem.getProductId()).getProductDTO();

            if(validateProductStock(productDTO, estimateItem.getQuantity())){
                estimateItem.setUnitPrice(productDTO.getUnitPrice());
            }else{
                log.error("EstimateServiceImpl.getUnitPrice - Product Out Of Stock");
                throw new ProductOutOfStockException(new ExceptionResponse(ErrorCodes.INVALID_REQUEST, PRODUCT_OUT_OF_STOCK));

            }
        });
    }

    private boolean validateProductStock(ProductDTO productDTO, Integer orderQuantity){
        return orderQuantity <= productDTO.getQuantityInStock();
    }
}
