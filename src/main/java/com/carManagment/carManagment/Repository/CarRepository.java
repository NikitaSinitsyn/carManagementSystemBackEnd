package com.carManagment.carManagment.Repository;

import com.carManagment.carManagment.Entitys.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByNumber(String number);
}