package com.carManagment.carManagment.Controller;

import com.carManagment.carManagment.Dto.CivilInsurance.CivilInsuranceDTO;
import com.carManagment.carManagment.Dto.CivilInsurance.CreateOrUpdateCivilInsuranceDTO;
import com.carManagment.carManagment.Service.CivilInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/civilInsurances")
public class CivilInsuranceController {

    private final CivilInsuranceService civilInsuranceService;

    @Autowired
    public CivilInsuranceController(CivilInsuranceService civilInsuranceService) {
        this.civilInsuranceService = civilInsuranceService;
    }

    @PostMapping
    public ResponseEntity<CivilInsuranceDTO> addCivilInsurance(@RequestBody CreateOrUpdateCivilInsuranceDTO createOrUpdateCivilInsuranceDTO) {
        CivilInsuranceDTO addedCivilInsurance = civilInsuranceService.addCivilInsurance(createOrUpdateCivilInsuranceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCivilInsurance);
    }

    @DeleteMapping("/{civilId}")
    public ResponseEntity<Void> deleteCivilInsurance(@PathVariable Long civilId) {
        civilInsuranceService.deleteCivilInsurance(civilId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{civilId}")
    public ResponseEntity<CivilInsuranceDTO> updateCivilInsurance(@PathVariable Long civilId,@RequestBody CreateOrUpdateCivilInsuranceDTO createOrUpdateCivilInsuranceDTO) {
        CivilInsuranceDTO updatedCivilInsurance = civilInsuranceService.updateCivilInsurance(civilId, createOrUpdateCivilInsuranceDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCivilInsurance);
    }

    @GetMapping("/{civilId}")
    public ResponseEntity<CivilInsuranceDTO> getCivilInsuranceById(@PathVariable Long civilId) {
        CivilInsuranceDTO civilInsurance = civilInsuranceService.getCivilInsuranceById(civilId);
        return ResponseEntity.status(HttpStatus.OK).body(civilInsurance);
    }

    @GetMapping("/All")
    public ResponseEntity<List<CivilInsuranceDTO>> getAllCivilInsurances() {
        List<CivilInsuranceDTO> civilInsurances = civilInsuranceService.getAllCivilInsurances();
        return ResponseEntity.status(HttpStatus.OK).body(civilInsurances);
    }
}
