package com.project.SWP391.services.ServiceImp;

import com.project.SWP391.repositories.MaterialRepository;
import com.project.SWP391.responses.dto.MaterialDTO;
import com.project.SWP391.services.MaterialService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImp implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public MaterialDTO createNewMaterial(MaterialDTO request) {
        return null;
    }

    @Override
    public MaterialDTO updateMaterial(String name, Long id) {
        return null;
    }



    @Override
    public List<MaterialDTO> getAllMaterials() {
        return null;
    }

    @Override
    public Long deleteMaterial(Long id) {
        return null;
    }
}
