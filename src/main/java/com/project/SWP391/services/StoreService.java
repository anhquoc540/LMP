package com.project.SWP391.services;

import com.project.SWP391.requests.SpecialServiceFilterRequest;
import com.project.SWP391.responses.dto.StoreInfoDTO;

import java.util.List;

public interface StoreService {
    StoreInfoDTO createStore(StoreInfoDTO request);
    StoreInfoDTO updateStore(StoreInfoDTO request, Long id);

    List<StoreInfoDTO> getStore();

    List<StoreInfoDTO> getAllStore();
    void deleteStore(Long id);

    List<StoreInfoDTO> getAllStoreByFilter(SpecialServiceFilterRequest request);
}
