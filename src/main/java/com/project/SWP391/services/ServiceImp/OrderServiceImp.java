package com.project.SWP391.services.ServiceImp;

import com.project.SWP391.entities.*;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
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
        var id = SecurityUtils.getPrincipal().getId();
        OffsetDateTime odt = OffsetDateTime.now() ;
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "uuuuMMddHHmmssSSSD" ) ;
        var user = userRepository.findById(id).orElseThrow();
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
    public List<OrderInfoDTO> getAllOrders(Long id) {
        var orders = orderRepository.findAllByUserId(id);

        List<OrderInfoDTO> list = orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());


        return list.stream().peek(orderInfoDTO -> orderInfoDTO.setOrderDate(convertDate(Long.parseLong(orderInfoDTO.getOrderDate())))).collect(Collectors.toList());
    }

    @Override
    public List<OrderInfoDTO> getAllOrdersByStore(Long id) {

        var store = storeRepository.findStoreByUserId(id);
        var orders = orderRepository.findAllByStoreId(store.getId());

        List<OrderInfoDTO> list = orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());


        return list.stream().peek(orderInfoDTO -> orderInfoDTO.setOrderDate(convertDate(Long.parseLong(orderInfoDTO.getOrderDate())))).collect(Collectors.toList());
    }

    @Override
    public List<OrderInfoDTO> getAllAcceptedOrdersByStaff() {

        var orders = orderRepository.findAll();
        Predicate<Order> byProcessing = order -> order.getStatus() == 2;
        List<OrderInfoDTO> list = orders.stream().filter(byProcessing).map(order -> mapToDTO(order)).collect(Collectors.toList());



        return list.stream().peek(orderInfoDTO -> orderInfoDTO.setOrderDate(convertDate(Long.parseLong(orderInfoDTO.getOrderDate())))).collect(Collectors.toList());
    }

    @Override
    public List<OrderInfoDTO> getAllDeliveryOrdersByStaff() {

        var orders = orderRepository.findAll();
        Predicate<Order> byProcessing = order -> order.getStatus() > 2;
        List<OrderInfoDTO> list = orders.stream().filter(byProcessing).map(order -> mapToDTO(order)).collect(Collectors.toList());



        return list.stream().peek(orderInfoDTO -> orderInfoDTO.setOrderDate(convertDate(Long.parseLong(orderInfoDTO.getOrderDate())))).collect(Collectors.toList());
    }

    @Override
    public OrderInfoDTO getAnOder(Long id) {

        var user = SecurityUtils.getPrincipal();
        var order = orderRepository.findById(id).orElseThrow();
        if(user.getRole().equals(Role.USER)){
            if (order.getUser().getId() != user.getId()){
                throw new IllegalArgumentException();
            }
        }

        if(user.getRole().equals(Role.STORE)){
            var store = storeRepository.findStoreByUserId(user.getId());
            if(order.getStore().getId() != store.getId()){
                throw new IllegalArgumentException();
            }
        }

        order.setTotal(0F);
        float total = 0F;
        var items = itemRepository.findAllByOrderId(id);
        for (Item item: items
        ) {
            total += item.getTotal();

        }
        order.setTotal(total);
        orderRepository.save(order);
        OrderInfoDTO dto = mapToDTO(order);
        DateFormat obj = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // we create instance of the Date and pass milliseconds to the constructor
        Date res = new Date(order.getOrderDate());
        dto.setOrderDate(obj.format(res));
        return dto;
    }

    @Override
    public void cancelAnOrder(Long id) {
        var user = SecurityUtils.getPrincipal();
        var order = orderRepository.findById(id).orElseThrow();
        if(user.getRole().equals(Role.USER)){
            if (order.getUser().getId() != user.getId()){
                throw new IllegalArgumentException();
            }
        }

        if(user.getRole().equals(Role.STORE)){
            var store = storeRepository.findStoreByUserId(user.getId());
            if(order.getStore().getId() != store.getId()){
                throw new IllegalArgumentException();
            }
        }
        order.setStatus(0);
        orderRepository.save(order);
    }

    @Override
    public OrderInfoDTO updateAnOrder(Long id, int request) {
        var user = SecurityUtils.getPrincipal();
        var order = orderRepository.findById(id).orElseThrow();
        if(user.getRole().equals(Role.USER)){
            if (order.getUser().getId() != user.getId()){
                throw new IllegalArgumentException();
            }
        }

        if(user.getRole().equals(Role.STORE)){
            var store = storeRepository.findStoreByUserId(user.getId());
            if(order.getStore().getId() != store.getId()){
                throw new IllegalArgumentException();
            }
        }
        order.setStatus(request);

        var  update = orderRepository.save(order);
        OrderInfoDTO dto = mapToDTO(update);
        DateFormat obj = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // we create instance of the Date and pass milliseconds to the constructor
        Date res = new Date(update.getOrderDate());
        dto.setOrderDate(obj.format(res));
        return dto;
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

    private String convertDate (Long date){
        DateFormat obj = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // we create instance of the Date and pass milliseconds to the constructor
        Date res = new Date(date);
        return  obj.format(res);
    }
}
