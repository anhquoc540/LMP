package com.project.SWP391.services.ServiceImp;

import com.project.SWP391.entities.Item;
import com.project.SWP391.entities.LaundryDetail;
import com.project.SWP391.entities.Order;
import com.project.SWP391.repositories.*;
import com.project.SWP391.requests.CreateOrderRequest;
import com.project.SWP391.requests.OrderUpdateRequest;
import com.project.SWP391.responses.dto.ItemInfoDTO;
import com.project.SWP391.responses.dto.LaundryDetailInfoDTO;
import com.project.SWP391.responses.dto.OrderInfoDTO;
import com.project.SWP391.security.utils.SecurityUtils;
import com.project.SWP391.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    @Autowired
        private OrderRepository orderRepository;

    @Autowired
    private LaundryDetailRepository laundryDetailRepository;

    @Autowired
    private LaundryServiceRepository serviceRepository;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public OrderInfoDTO createOrder(CreateOrderRequest request) {
        OffsetDateTime odt = OffsetDateTime.now() ;
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "uuuuMMddHHmmssSSSD" ) ;
        var user = userRepository.findById(22L).orElseThrow();
        var store = storeRepository.findById(request.getStoreId()).orElseThrow();
        var order = Order.builder().orderDate(request.getCreateDate())
                .status(1)
                .store(store).total(request.getTotal())
                .orderCode("ORD"+odt.format(f))
                .user(user)
                .build();
        orderRepository.save(order);
        var items = itemRepository.saveAll(request.getItems().stream()
                .map(itemReqDTO -> Item.builder()
                        .total(itemReqDTO.getPrice() * itemReqDTO.getQuantity())
                        .quantity(itemReqDTO.getQuantity())
                        .laundryService(serviceRepository.findById(itemReqDTO.getId()).orElseThrow())
                        .order(order)
                        .build())

                .collect(Collectors.toList()));
        var newOrder = orderRepository.findById(order.getId()).orElseThrow();
        return mapToDTO(newOrder);
    }

    @Override
    public List<OrderInfoDTO> getAllOrders() {
        var orders = orderRepository.findAllByUserId(22L);

        return orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderInfoDTO> getAllOrdersByStore() {

        var store = storeRepository.findStoreByUserId(SecurityUtils.getPrincipal().getId());
        var orders = orderRepository.findAllByStoreId(store.getId());
        return orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
    }

    @Override
    public OrderInfoDTO getAnOder(Long id) {
        var order = orderRepository.findById(id).orElseThrow();
        order.setTotal(0F);
        float total = 0;
        var items = itemRepository.findAllByOrderId(id);
        for (Item item: items
        ) {
            total += item.getTotal();

        }
        order.setTotal(total);
        return mapToDTO(order);
    }

    @Override
    public void cancelAnOrder(Long id) {

    }

    @Override
    public OrderInfoDTO updateAnOrder(Long id, OrderUpdateRequest request) {
        var order = orderRepository.findById(22L).orElseThrow();
        order.setStatus(request.getStatus());
        var items = itemRepository.findAllByOrderId(id);
        order.setTotal(0F);
        float total = 0;
        for (Item item: items
             ) {
            total += item.getTotal();

        }
        order.setTotal(total);
        var  update = orderRepository.save(order);
        return mapToDTO(update);
    }

    @Override
    public ItemInfoDTO updateItemOfAnOrder(Long id, Float weight) {
        var item = itemRepository.findById(id).orElseThrow();
        item.setWeight(weight);
        var detail = laundryDetailRepository.findAllByLaundryServiceId(item.getLaundryService().getId());
        for (LaundryDetail dto: detail
             ) {
            if(weight >= dto.getFrom() &&  weight <= dto.getTo()){
                item.setTotal(weight * dto.getPrice());
            }

        }
        var update = itemRepository.save(item);
        return mapper.map(update, ItemInfoDTO.class);
    }

    private OrderInfoDTO mapToDTO(Order dto){
        return mapper.map(dto, OrderInfoDTO.class);
    }
}
