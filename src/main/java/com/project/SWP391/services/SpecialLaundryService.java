package com.project.SWP391.services;

import com.project.SWP391.repositories.ClotheRepository;
import com.project.SWP391.repositories.MaterialRepository;
import com.project.SWP391.repositories.SpecialServiceRepository;
import com.project.SWP391.repositories.StoreRepository;
import com.project.SWP391.requests.SpecialServiceRequest;
import com.project.SWP391.responses.dto.SpecialServiceInfoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.SWP391.entities.SpecialLaundry;

import java.util.List;
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


        var store = storeRepository.findById(request.getStoreId()).orElseThrow();
        var cloth = clotheRepository.findById(request.getClothId()).orElseThrow();
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

    public SpecialServiceInfoDTO updateSpecialService(SpecialServiceRequest request, long id) {
        var editSpecialService = serviceRepository.findById(id).orElseThrow();
        var cloth = clotheRepository.findById(request.getClothId()).orElseThrow();
        var material = materialRepository.findAllById(request.getMaterials()).stream().collect(Collectors.toSet());

        editSpecialService.setName(request.getName());
        editSpecialService.setDescription(request.getDescription());
        editSpecialService.setUnit(request.getUnit());
        editSpecialService.setMaterials(material);
        editSpecialService.setIsDeleted(request.getIsDeleted());
        editSpecialService.setPrice(request.getPrice());
        editSpecialService.setImageBanner(request.getImage());
        editSpecialService.setCloth(cloth);

        var newService = serviceRepository.save(editSpecialService);
        return  mapToDTO(newService);

    }
}



