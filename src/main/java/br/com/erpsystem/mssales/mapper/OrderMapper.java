package br.com.erpsystem.mssales.mapper;

import br.com.erpsystem.mssales.dto.EstimateDTO;
import br.com.erpsystem.mssales.dto.EstimateItemDTO;
import br.com.erpsystem.mssales.dto.OrderDTO;
import br.com.erpsystem.mssales.dto.OrderItemDTO;
import br.com.erpsystem.mssales.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderDTOToOrder(OrderDTO orderDTO);
    OrderDTO orderToOrderDTO(Order order);

    List<Order> ordersDTOToOrders(List<OrderDTO> orderDTOS);
    List<OrderDTO> ordersToOrdersDTO(List<Order> orders);

    Set<OrderItemDTO> estimateItemDTOTOOrderItemDTO (Set<EstimateItemDTO> estimateItems);

    OrderDTO estimateDTOToOrderDTO (EstimateDTO estimateDTO);

}
