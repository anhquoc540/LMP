package com.project.SWP391.controllers;

import com.project.SWP391.requests.SpecialServiceRequest;
import com.project.SWP391.requests.StandardServiceRequest;
import com.project.SWP391.responses.dto.SpecialServiceInfoDTO;
import com.project.SWP391.responses.dto.StandardServiceInfoDTO;
import com.project.SWP391.security.utils.SecurityUtils;
import com.project.SWP391.services.SpecialLaundryService;
import com.project.SWP391.services.StandardLaundryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store")
@PreAuthorize("hasRole('STORE')")
@RequiredArgsConstructor
@Tag(name = "Store", description = "Store management APIs")
public class StoreController {
    private final SpecialLaundryService service;
    private final StandardLaundryService standardLaundryService;

   @GetMapping("/special-service/all")
   @PreAuthorize("hasAuthority('store:read')")
    public ResponseEntity<List<SpecialServiceInfoDTO>> getAllSpecialServices() {
        return ResponseEntity.ok(service.getAllSpecialServiceForStore());
    }

    @GetMapping("/special-service/get/{id}")
    @PreAuthorize("hasAuthority('store:read')")
    public ResponseEntity<SpecialServiceInfoDTO> getSpecialServicesById(@PathVariable(name = "id") long id) {
       return  ResponseEntity.ok(service.getSpecialServiceForStore(id));
    }

    @GetMapping("/standard-service/get")
    @PreAuthorize("hasAuthority('store:read')")
    public ResponseEntity<StandardServiceInfoDTO> getStandardServicesById() {
        return  ResponseEntity.ok(standardLaundryService.getStandardServiceForStore());
    }

    @PostMapping("/special-service/create")
    @PreAuthorize("hasAuthority('store:create')")
    public ResponseEntity<SpecialServiceInfoDTO> createSpecialService(@RequestBody SpecialServiceRequest request) {
        return ResponseEntity.ok(service.CreateSpecialServiceByStoreId(request));
    }

    @PostMapping("/standard-service/create")
    @PreAuthorize("hasAuthority('store:create')")
    public ResponseEntity<StandardServiceInfoDTO> createStandardService(@RequestBody StandardServiceRequest request) {
        return ResponseEntity.ok(standardLaundryService.createStandardService(request));
    }

    @PutMapping("/special-service/update/{id}")
    @PreAuthorize("hasAuthority('store:update')")
    public ResponseEntity<SpecialServiceInfoDTO> updateSpecialService(@RequestBody SpecialServiceRequest request,@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(service.updateSpecialService(request,id));
    }

    @DeleteMapping("/special-service/delete/{id}")
    @PreAuthorize("hasAuthority('store:delete')")
    public ResponseEntity<SpecialServiceInfoDTO> deleteSpecialService(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(service.deleteSpecialService(id));
    }

    @PutMapping("/standard-service/update/{id}")
    @PreAuthorize("hasAuthority('store:update')")
    public ResponseEntity<StandardServiceInfoDTO> updateStandardService(@RequestBody StandardServiceRequest request,@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(standardLaundryService.updateStandardService(request,id));
    }

    @DeleteMapping("/standard-service/delete/{id}")
    @PreAuthorize("hasAuthority('store:delete')")
    public ResponseEntity<StandardServiceInfoDTO> deleteStandardService(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(standardLaundryService.deleteSpecialService(id));
    }


}
