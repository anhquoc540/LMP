package com.project.SWP391.controllers;

import com.project.SWP391.requests.SpecialServiceRequest;
import com.project.SWP391.responses.dto.SpecialServiceInfoDTO;
import com.project.SWP391.services.SpecialLaundryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store")
@PreAuthorize("hasRole('STORE')")
@RequiredArgsConstructor
public class ServiceController {
    private final SpecialLaundryService service;
    @GetMapping("/special-service/all/{id}")
    @PreAuthorize("hasAuthority('store:read')")
    public ResponseEntity<List<SpecialServiceInfoDTO>> getAllServicesByStoreId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(service.getAllSpecialServiceByStoreId(id));
    }

    @PostMapping("/special-service/create")
    @PreAuthorize("hasAuthority('store:create')")
    public ResponseEntity<SpecialServiceInfoDTO> createSpecialService(@RequestBody SpecialServiceRequest request) {
        return ResponseEntity.ok(service.CreateSpecialServiceByStoreId(request));
    }


}
