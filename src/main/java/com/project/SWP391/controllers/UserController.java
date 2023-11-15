package com.project.SWP391.controllers;
import com.project.SWP391.requests.SpecialServiceFilterRequest;
import com.project.SWP391.requests.SpecialServiceRequest;
import com.project.SWP391.responses.dto.OrderInfoDTO;
import com.project.SWP391.responses.dto.StoreInfoDTO;
import com.project.SWP391.responses.dto.UserInfoDTO;
import com.project.SWP391.services.OrderService;
import com.project.SWP391.services.StoreService;
import com.project.SWP391.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('USER')")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "User", description = "User management APIs")
public class UserController
{
    private final UserService service ;

    private final OrderService orderService;

//    @GetMapping("/profile")
//    public ResponseEntity<UserInfoDTO> getProfile() {
//        return ResponseEntity.ok(service.getCurrentUser());
//    }

    @GetMapping("/profile/update/{id}")
    public ResponseEntity<UserInfoDTO> updateProfile(@PathVariable Long id , @RequestBody UserInfoDTO request) {
        return ResponseEntity.ok(service.updateUser(id,request));
    }


    @GetMapping("/order/all/{id}")
    public ResponseEntity<List<OrderInfoDTO>> getOrders(@PathVariable("id") long id) {
        return ResponseEntity.ok(orderService.getAllOrders(id));
    }


    @DeleteMapping("/order/cancel/{id}")

    public ResponseEntity cancelAnOrder(@PathVariable("id") long id){
        orderService.cancelAnOrder(id);
        return  ResponseEntity.ok().build();
    }

}
