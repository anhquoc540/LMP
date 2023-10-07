package com.project.SWP391.services;

import com.project.SWP391.entities.Material;
import com.project.SWP391.entities.Store;
import com.project.SWP391.repositories.ClotheRepository;
import com.project.SWP391.repositories.MaterialRepository;
import com.project.SWP391.repositories.SpecialServiceRepository;
import com.project.SWP391.repositories.StoreRepository;
import com.project.SWP391.requests.SpecialServiceRequest;
import com.project.SWP391.responses.SpecialServiceInfoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.SWP391.entities.SpecialLaundry;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SpecialLaundryService{

    private  final SpecialServiceRepository serviceRepository;
    private  final StoreRepository storeRepository;

    private  final ClotheRepository clotheRepository;

    private  final MaterialRepository materialRepository;
    @Autowired
    private final ModelMapper mapper;

    public List<SpecialServiceInfoDTO> getAllSpecialService(){

        List<SpecialLaundry> laundries = serviceRepository.findAll();
        return  laundries.stream().map(service -> mapToDTO(service)).collect(Collectors.toList());

    }

    public List<SpecialServiceInfoDTO> getAllSpecialServiceByStoreId(Long storeId){

        List<SpecialLaundry> laundries = serviceRepository.findAllByStoreId(storeId);
        return  laundries.stream().map(service -> mapToDTO(service)).collect(Collectors.toList());

    }

    public SpecialServiceInfoDTO CreateSpecialServiceByStoreId(SpecialServiceRequest request){
        Long id = Long.valueOf(request.getStoreId());
        Long clothId = Long.valueOf(request.getClotheId());
        var store = storeRepository.findById(id).orElseThrow();
        var cloth = clotheRepository.findById(clothId).orElseThrow();
        var material = materialRepository.findAllById(request.getMaterials()).stream().collect(Collectors.toSet());
        var laundry = SpecialLaundry.builder().name(request.getName())
               .store(store)
                .materials(material)
                .cloth(cloth)
                .description(request.getDescription())
                .unit(request.getUnit())
                .price(request.getPrice())
                .imageBanner(request.getImage())
                .build();

        var newService = serviceRepository.save(laundry);
        return  mapToDTO(newService);

    }


    private SpecialServiceInfoDTO mapToDTO(SpecialLaundry dto) {
        return mapper.map(dto, SpecialServiceInfoDTO.class);
    }
}



