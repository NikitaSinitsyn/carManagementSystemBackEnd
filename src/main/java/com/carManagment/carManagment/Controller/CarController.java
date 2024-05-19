package com.carManagment.carManagment.Controller;

import com.carManagment.carManagment.Dto.Car.CarDTO;
import com.carManagment.carManagment.Dto.Car.CreateOrUpdateCarDTO;
import com.carManagment.carManagment.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDTO> addCar(@RequestBody CreateOrUpdateCarDTO createOrUpdateCarDTO) {
        CarDTO addedCar = carService.addCar(createOrUpdateCarDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCar);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long carId, @RequestBody CreateOrUpdateCarDTO createOrUpdateCarDTO) {
        CarDTO updatedCar = carService.updateCar(carId, createOrUpdateCarDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long carId) {
        CarDTO car = carService.getCarById(carId);
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

    @GetMapping("/All")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAllCars();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }
    @GetMapping("/view/{carId}")
    public ResponseEntity<CarDTO> getCarDetails(@PathVariable Long carId) {
        CarDTO carDTO = carService.getCarDetails(carId);
        return ResponseEntity.status(HttpStatus.OK).body(carDTO);
    }
}
