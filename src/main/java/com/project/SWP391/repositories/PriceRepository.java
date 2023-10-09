package com.project.SWP391.repositories;

import com.project.SWP391.entities.PriceBasedWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.PublicKey;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceBasedWeight, Long> {
    public List<PriceBasedWeight> findAllByStandardLaundryId(Long id);
    public List<PriceBasedWeight> deleteAllByStandardLaundryId(Long id);
}
