package com.project.SWP391.repositories;


import com.project.SWP391.entities.StandardLaundry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardServiceRepository extends JpaRepository<StandardLaundry, Long> {
    List<StandardLaundry> findAllByStoreId(Long id);
}
