package com.carManagment.carManagment.Repository;

import com.carManagment.carManagment.Entitys.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
}
