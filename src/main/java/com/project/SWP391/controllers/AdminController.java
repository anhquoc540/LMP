package com.project.SWP391.controllers;

import com.project.SWP391.requests.SpecialServiceRequest;

import com.project.SWP391.responses.dto.UserInfoDTO;

import com.project.SWP391.services.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

@RequestMapping("/api/v1/admin")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final UserService service;



    @GetMapping("/user/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<UserInfoDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @PutMapping("/user/update/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<UserInfoDTO> disableUser(@PathVariable Long id, int status) {
        return ResponseEntity.ok(service.updateUserForAdmin(id, status));
    }

    @DeleteMapping("/user/delete/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<UserInfoDTO> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
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
