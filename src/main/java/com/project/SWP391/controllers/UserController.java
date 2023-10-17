package com.project.SWP391.controllers;

import com.project.SWP391.requests.SpecialServiceFilterRequest;
import com.project.SWP391.requests.SpecialServiceRequest;
import com.project.SWP391.responses.dto.StoreInfoDTO;
import com.project.SWP391.services.StoreService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class UserController
{

    private final StoreService service ;


    @GetMapping("special-service/filter")
    public ResponseEntity<List<StoreInfoDTO>> getAllStoresByFilter(@RequestBody SpecialServiceFilterRequest request){
        return ResponseEntity.ok(service.getAllStoreByFilter(request));
    }

}
