package com.project.SWP391.repositories;

import com.project.SWP391.entities.PriceBasedWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceBasedWeight, Long> {
    public List<PriceBasedWeight> findAllByStandardLaundryId(Long id);

}
