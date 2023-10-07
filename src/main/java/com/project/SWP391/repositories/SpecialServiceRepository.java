package com.project.SWP391.repositories;

import com.project.SWP391.entities.SpecialLaundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface SpecialServiceRepository extends JpaRepository<SpecialLaundry, Long>, JpaSpecificationExecutor<SpecialLaundry> {
    List<SpecialLaundry> findAllByStoreId(Long id);


}
