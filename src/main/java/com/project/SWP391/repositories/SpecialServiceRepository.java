package com.project.SWP391.repositories;

import com.project.SWP391.entities.SpecialLaundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface SpecialServiceRepository extends JpaRepository<SpecialLaundry, Long>, JpaSpecificationExecutor<SpecialLaundry> {
    List<SpecialLaundry> findAllByStoreId(Long id);

    List<SpecialLaundry> findAllByClothId(Long id);

    @Query(value = "SELECT DISTINCT store.id "
            + "FROM SpecialLaundry "
            + "WHERE id IN :ids ")
    List<Long> findAllStoreByFilter(@Param("ids") List<Long> specialIds);



}
