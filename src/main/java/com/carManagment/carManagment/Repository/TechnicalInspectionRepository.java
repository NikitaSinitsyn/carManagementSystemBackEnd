package com.carManagment.carManagment.Repository;

import com.carManagment.carManagment.Entitys.TechnicalInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalInspectionRepository extends JpaRepository<TechnicalInspection, Long> {
}