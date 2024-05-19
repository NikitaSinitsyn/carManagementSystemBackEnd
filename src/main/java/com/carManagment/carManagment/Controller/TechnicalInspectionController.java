package com.carManagment.carManagment.Controller;

import com.carManagment.carManagment.Dto.TechnicalInspectation.CreateOrUpdateTechnicalInspectionDTO;
import com.carManagment.carManagment.Dto.TechnicalInspectation.TechnicalInspectionDTO;
import com.carManagment.carManagment.Service.TechnicalInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicalInspections")
public class TechnicalInspectionController {

    private final TechnicalInspectionService technicalInspectionService;

    @Autowired
    public TechnicalInspectionController(TechnicalInspectionService technicalInspectionService) {
        this.technicalInspectionService = technicalInspectionService;
    }

    @PostMapping
    public ResponseEntity<TechnicalInspectionDTO> addTechnicalInspection(@RequestBody CreateOrUpdateTechnicalInspectionDTO createOrUpdateTechnicalInspectionDTO) {
        TechnicalInspectionDTO createdTechnicalInspection = technicalInspectionService.addTechnicalInspection(createOrUpdateTechnicalInspectionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTechnicalInspection);
    }

    @DeleteMapping("/{technicalInspectionId}")
    public ResponseEntity<Void> deleteTechnicalInspection(@PathVariable Long technicalInspectionId) {
        technicalInspectionService.deleteTechnicalInspection(technicalInspectionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{technicalInspectionId}")
    public ResponseEntity<TechnicalInspectionDTO> updateTechnicalInspection(@PathVariable Long technicalInspectionId, @RequestBody CreateOrUpdateTechnicalInspectionDTO createOrUpdateTechnicalInspectionDTO) {
        TechnicalInspectionDTO updatedTechnicalInspection = technicalInspectionService.updateTechnicalInspection(technicalInspectionId, createOrUpdateTechnicalInspectionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTechnicalInspection);
    }

    @GetMapping("/{technicalInspectionId}")
    public ResponseEntity<TechnicalInspectionDTO> getTechnicalInspectionById(@PathVariable Long technicalInspectionId) {
        TechnicalInspectionDTO technicalInspectionDTO = technicalInspectionService.getTechnicalInspectionById(technicalInspectionId);
        return ResponseEntity.status(HttpStatus.OK).body(technicalInspectionDTO);
    }

    @GetMapping("/All")
    public ResponseEntity<List<TechnicalInspectionDTO>> getAllTechnicalInspections() {
        List<TechnicalInspectionDTO> allTechnicalInspections = technicalInspectionService.getAllTechnicalInspections();
        return ResponseEntity.status(HttpStatus.OK).body(allTechnicalInspections);
    }
}
