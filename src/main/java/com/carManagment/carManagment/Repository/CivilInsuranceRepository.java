package com.carManagment.carManagment.Repository;

import com.carManagment.carManagment.Entitys.CivilInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CivilInsuranceRepository extends JpaRepository<CivilInsurance, Long> {
}
