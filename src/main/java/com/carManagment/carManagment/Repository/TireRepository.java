package com.carManagment.carManagment.Repository;

import com.carManagment.carManagment.Entitys.Tire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TireRepository extends JpaRepository<Tire, Long> {
}
