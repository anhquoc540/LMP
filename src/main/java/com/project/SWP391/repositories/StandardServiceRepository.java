package com.project.SWP391.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardServiceRepository extends JpaRepository<StandardLaundry, Long> {
    StandardLaundry findByStoreId(Long id);
}
