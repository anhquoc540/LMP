package com.project.SWP391.repositories;

import com.project.SWP391.entities.SpecialLaundry;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface SpecialServiceRepository extends JpaRepository<SpecialLaundry, Long> {
    List<SpecialLaundry> findAllByStoreId(Long id);

    List<SpecialLaundry> findAllByClothId(Long id);

}
