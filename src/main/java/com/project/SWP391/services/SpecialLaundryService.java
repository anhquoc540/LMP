package com.project.SWP391.services;

import com.project.SWP391.entities.User;
import com.project.SWP391.repositories.ClotheRepository;
import com.project.SWP391.repositories.MaterialRepository;
import com.project.SWP391.repositories.SpecialServiceRepository;
import com.project.SWP391.repositories.StoreRepository;
import com.project.SWP391.requests.SpecialServiceRequest;
import com.project.SWP391.responses.dto.SpecialServiceInfoDTO;
import com.project.SWP391.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.project.SWP391.entities.SpecialLaundry;

import java.util.List;
import java.util.function.Predicate;
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








    public List<SpecialServiceInfoDTO> getAllSpecialServiceForStore(){
        var store = storeRepository.findStoreByUserId(SecurityUtils.getPrincipal().getId());
        List<SpecialLaundry> laundries = serviceRepository.findAllByStoreId(store.getId());
        Predicate<SpecialLaundry> byDeleted = specialLaundry -> specialLaundry.getIsDeleted() == 0;
        return  laundries.stream().filter(byDeleted).map(service -> mapToDTO(service)).collect(Collectors.toList());

    }
    public SpecialServiceInfoDTO getSpecialServiceForStore(Long id){
        var laundry = serviceRepository.findById(id).orElseThrow();
        var store = storeRepository.findStoreByUserId(SecurityUtils.getPrincipal().getId());
        if((laundry.getStore()).getId() != store.getId()){
            throw new RuntimeException("You don't have permission to access this action");
        }
        return mapToDTO(laundry);

    }

    public List<SpecialServiceInfoDTO> getAllSpecialServiceByStoreForCustomer(Long id){
        List<SpecialLaundry> laundries = serviceRepository.findAllByStoreId(id);
        Predicate<SpecialLaundry> byDeleted = specialLaundry -> specialLaundry.getIsDeleted() == 0;
        return  laundries.stream().filter(byDeleted).map(service -> mapToDTO(service)).collect(Collectors.toList());

    }

    public List<SpecialServiceInfoDTO> getAllSpecialServiceForCustomer(){

        List<SpecialLaundry> laundries = serviceRepository.findAll();
        Predicate<SpecialLaundry> byDeleted = specialLaundry -> specialLaundry.getIsDeleted() == 0;
        return  laundries.stream().filter(byDeleted).map(service -> mapToDTO(service)).collect(Collectors.toList());

    }

    public SpecialServiceInfoDTO getSpecialServiceCustomer(Long id){
        var laundry = serviceRepository.findById(id).orElseThrow();
        return mapToDTO(laundry);

    }

    public SpecialServiceInfoDTO CreateSpecialServiceByStoreId(SpecialServiceRequest request){


        var store = storeRepository.findStoreByUserId(SecurityUtils.getPrincipal().getId());
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
                .isDeleted(0)
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

        if(editSpecialService.getIsDeleted() == 1){
           throw new RuntimeException("Service is not found");
        }

        editSpecialService.setName(request.getName());
        editSpecialService.setDescription(request.getDescription());
        editSpecialService.setUnit(request.getUnit());
        editSpecialService.setMaterials(material);
        editSpecialService.setPrice(request.getPrice());
        editSpecialService.setImageBanner(request.getImage());
        editSpecialService.setCloth(cloth);

        var newService = serviceRepository.save(editSpecialService);
        return  mapToDTO(newService);

    }

    public void deleteSpecialService(long id) {
        var editSpecialService = serviceRepository.findById(id).orElseThrow();

        editSpecialService.setIsDeleted(1);

        var newService = serviceRepository.save(editSpecialService);


    }


}



