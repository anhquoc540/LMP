package com.project.SWP391.controllers;

import com.project.SWP391.requests.SpecialServiceRequest;

import com.project.SWP391.services.SpecialLaundryService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final SpecialLaundryService service;

    @GetMapping("/user/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAllUsers() {
        return "GET:: admin controller";
    }

    @GetMapping("/store/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAllStores() {
        return "GET:: admin controller";
    }



    @GetMapping("/type/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAllTypes() {
        return "GET:: admin controller";
    }

    @GetMapping("/material/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAllMaterials() {
        return "GET:: admin controller";
    }

    @GetMapping("/time-category/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAllTimeCategories() {
        return "GET:: admin controller";
    }


    @PostMapping("/type/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public String createNewType() {
        return "GET:: admin controller";
    }

    @PostMapping("/material/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public String createNewMaterial() {
        return "GET:: admin controller";
    }

    @PostMapping("/time-category/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public String createNewTimeCategory() {
        return "GET:: admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }


    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }
}
