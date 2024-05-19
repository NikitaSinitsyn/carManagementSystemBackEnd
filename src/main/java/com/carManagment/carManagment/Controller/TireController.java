package com.carManagment.carManagment.Controller;

import com.carManagment.carManagment.Dto.Tire.CreateOrUpdateTireDTO;
import com.carManagment.carManagment.Dto.Tire.TireDTO;
import com.carManagment.carManagment.Entitys.Season;
import com.carManagment.carManagment.Service.TireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/tires")
public class TireController {

    private final TireService tireService;

    @Autowired
    public TireController(TireService tireService) {
        this.tireService = tireService;
    }

    @PostMapping
    public ResponseEntity<TireDTO> addTire(@RequestBody CreateOrUpdateTireDTO createOrUpdateTireDTO) {
        TireDTO addedTire = tireService.addTire(createOrUpdateTireDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTire);
    }

    @DeleteMapping("/{tireId}")
    public ResponseEntity<Void> deleteTire(@PathVariable Long tireId) {
        tireService.deleteTire(tireId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{tireId}")
    public ResponseEntity<TireDTO> updateTire(@PathVariable Long tireId, @RequestBody CreateOrUpdateTireDTO createOrUpdateTireDTO) {
        TireDTO updatedTire = tireService.updateTire(tireId, createOrUpdateTireDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTire);
    }

    @GetMapping("/{tireId}")
    public ResponseEntity<TireDTO> getTireById(@PathVariable Long tireId) {
        TireDTO tire = tireService.getTireById(tireId);
        return ResponseEntity.status(HttpStatus.OK).body(tire);
    }

    @GetMapping("/All")
    public ResponseEntity<List<TireDTO>> getAllTires() {
        List<TireDTO> tires = tireService.getAllTires();
        return ResponseEntity.status(HttpStatus.OK).body(tires);
    }

    @GetMapping("/car/{carNumber}/season/{season}")
    public ResponseEntity<List<TireDTO>> getTiresByCarNumberAndSeason(@PathVariable("carNumber") String carNumber, @PathVariable("season") Season season) {
        List<TireDTO> tires = tireService.getTiresByCarNumberAndSeason(carNumber, season);
        return new ResponseEntity<>(tires, HttpStatus.OK);
    }

    @DeleteMapping("/car/{carNumber}")
    public ResponseEntity<Void> deleteTiresByCarNumber(@PathVariable("carNumber") String carNumber) {
        tireService.deleteTiresByCarNumber(carNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/season/{season}")
    public ResponseEntity<List<TireDTO>> getTiresBySeason(@PathVariable("season") Season season) {
        List<TireDTO> tires = tireService.getTiresBySeason(season);
        return new ResponseEntity<>(tires, HttpStatus.OK);
    }

    @PutMapping("/car/{carNumber}/update/season/{season}")
    public ResponseEntity<List<TireDTO>> updateTiresByCarNumber(@PathVariable("carNumber") String carNumber, @RequestBody List<TireDTO> tireDTOs, @PathVariable("season") Season season) {
        List<TireDTO> updatedTires = tireService.updateTiresByCarNumber(carNumber, tireDTOs, season);
        return new ResponseEntity<>(updatedTires, HttpStatus.OK);
    }
}