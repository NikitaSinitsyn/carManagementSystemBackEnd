package com.carManagment.carManagment.Service;

import com.carManagment.carManagment.Dto.Car.CarDTO;
import com.carManagment.carManagment.Dto.Car.CreateOrUpdateCarDTO;

import java.util.List;

public interface CarService {
    CarDTO addCar(CreateOrUpdateCarDTO createOrUpdateCarDTO);

    void deleteCar(Long carId);

    CarDTO updateCar(Long carId, CreateOrUpdateCarDTO createOrUpdateCarDTO);

    CarDTO getCarById(Long carId);

    List<CarDTO> getAllCars();
    CarDTO findCarByNumber(String carNumber);
    CarDTO getCarDetails(Long carId);
}
