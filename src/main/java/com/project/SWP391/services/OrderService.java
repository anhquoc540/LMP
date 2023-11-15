package com.project.SWP391.services;

import com.project.SWP391.requests.CreateOrderRequest;
import com.project.SWP391.requests.OrderUpdateRequest;
import com.project.SWP391.responses.dto.ItemInfoDTO;
import com.project.SWP391.responses.dto.OrderInfoDTO;

import java.util.List;

public interface OrderService {
    OrderInfoDTO createOrder(CreateOrderRequest request);
    List<OrderInfoDTO> getAllOrders(Long id);
    List<OrderInfoDTO> getAllOrdersByStore(Long id);

    List<OrderInfoDTO> getAllAcceptedOrdersByStaff();

    List<OrderInfoDTO> getAllDeliveryOrdersByStaff();
    OrderInfoDTO getAnOder(Long id);

    void cancelAnOrder(Long id);

    OrderInfoDTO updateAnOrder (Long id ,int request);

    ItemInfoDTO updateItemOfAnOrder(Long id, Float weight);

}
