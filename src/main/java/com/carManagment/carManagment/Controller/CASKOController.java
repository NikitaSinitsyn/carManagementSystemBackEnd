package com.carManagment.carManagment.Controller;


import com.carManagment.carManagment.Dto.Casko.CASKODTO;
import com.carManagment.carManagment.Dto.Casko.CreateOrUpdateCaskoDTO;

import com.carManagment.carManagment.Service.CASKOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/caskos")
public class CASKOController {

    private final CASKOService caskoService;

    @Autowired
    public CASKOController(CASKOService caskoService) {
        this.caskoService = caskoService;
    }

    @PostMapping
    public ResponseEntity<CASKODTO> addCASKO(@RequestBody CreateOrUpdateCaskoDTO createOrUpdateCaskoDTO) {
        CASKODTO addedCASKO = caskoService.addCASKO(createOrUpdateCaskoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCASKO);
    }

    @DeleteMapping("/{caskoId}")
    public ResponseEntity<Void> deleteCASKO(@PathVariable Long caskoId) {
        caskoService.deleteCASKO(caskoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{caskoId}")
    public ResponseEntity<CASKODTO> updateCASKO(@PathVariable Long caskoId, @RequestBody CreateOrUpdateCaskoDTO createOrUpdateCaskoDTO) {
        CASKODTO updatedCASKO = caskoService.updateCASKO(caskoId, createOrUpdateCaskoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCASKO);
    }

    @GetMapping("/{caskoId}")
    public ResponseEntity<CASKODTO> getCASKOById(@PathVariable Long caskoId) {
        CASKODTO casko = caskoService.getCASKOById(caskoId);
        return ResponseEntity.status(HttpStatus.OK).body(casko);
    }

    @GetMapping("/All")
    public ResponseEntity<List<CASKODTO>> getAllCASKOs() {
        List<CASKODTO> caskos = caskoService.getAllCASKOs();
        return ResponseEntity.status(HttpStatus.OK).body(caskos);
    }
}