package com.project.SWP391.services.ServiceImp;


import com.project.SWP391.entities.SpecialLaundry;

import com.project.SWP391.entities.Store;
import com.project.SWP391.entities.User;
import com.project.SWP391.repositories.SpecialServiceRepository;
import com.project.SWP391.repositories.StoreRepository;
import com.project.SWP391.requests.SpecialServiceFilterRequest;
import com.project.SWP391.responses.dto.SpecialServiceInfoDTO;
import com.project.SWP391.responses.dto.StoreInfoDTO;
import com.project.SWP391.responses.dto.UserInfoDTO;
import com.project.SWP391.services.StoreService;
import com.project.SWP391.specifications.CustomServiceSpec;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImp implements StoreService {

    private final SpecialServiceRepository specialServiceRepository;
    private final StoreRepository storeRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public StoreInfoDTO createStore(StoreInfoDTO request) {
        return null;
    }

    @Override
    public StoreInfoDTO updateStore(StoreInfoDTO request, Long id) {
        return null;
    }

    @Override
    public List<StoreInfoDTO> getStore() {
        return null;


    }

    @Override
    public List<StoreInfoDTO> getAllStore() {

        return null;
    }

    @Override
    public void deleteStore(Long id) {

    }

    @Override
    public List<StoreInfoDTO> getAllStoreByFilter(SpecialServiceFilterRequest request) {
        CustomServiceSpec spec = new CustomServiceSpec(request);
        var list = specialServiceRepository.findAll(spec);
        List<Long> ids = new ArrayList<>();
        for (SpecialLaundry item : list
             ) {

            ids.add(item.getId() );

        }

        var stores = storeRepository.findAllById(specialServiceRepository.findAllStoreByFilter(ids));

        return stores.stream().map(store -> mapToDTO(store)).collect(Collectors.toList());

    }

    private StoreInfoDTO mapToDTO(Store dto) {
        return mapper.map(dto, StoreInfoDTO.class);
    }
}
