package com.project.SWP391.services;

import com.project.SWP391.requests.CreateOrderRequest;
import com.project.SWP391.requests.OrderUpdateRequest;
import com.project.SWP391.responses.dto.ItemInfoDTO;
import com.project.SWP391.responses.dto.OrderInfoDTO;

import java.util.List;

public interface OrderService {
    OrderInfoDTO createOrder(CreateOrderRequest request);
    List<OrderInfoDTO> getAllOrders();
    List<OrderInfoDTO> getAllOrdersByStore();


    OrderInfoDTO getAnOder(Long id);

    void cancelAnOrder(Long id);

    OrderInfoDTO updateAnOrder (Long id , OrderUpdateRequest orderUpdateRequest);

    ItemInfoDTO updateItemOfAnOrder(Long id, Float weight);

}
