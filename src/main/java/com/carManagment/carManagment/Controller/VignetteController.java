package com.carManagment.carManagment.Controller;

import com.carManagment.carManagment.Dto.Vignette.CreateOrUpdateVignetteDTO;
import com.carManagment.carManagment.Dto.Vignette.VignetteDTO;
import com.carManagment.carManagment.Service.VignetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vignettes")
public class VignetteController {

    private final VignetteService vignetteService;

    @Autowired
    public VignetteController(VignetteService vignetteService) {
        this.vignetteService = vignetteService;
    }

    @PostMapping
    public ResponseEntity<VignetteDTO> addVignette(@RequestBody CreateOrUpdateVignetteDTO createOrUpdateVignetteDTO) {
        VignetteDTO addedVignette = vignetteService.addVignette(createOrUpdateVignetteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedVignette);
    }

    @DeleteMapping("/{vignetteId}")
    public ResponseEntity<Void> deleteVignette(@PathVariable Long vignetteId) {
        vignetteService.deleteVignette(vignetteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{vignetteId}")
    public ResponseEntity<VignetteDTO> updateVignette(@PathVariable Long vignetteId, @RequestBody  CreateOrUpdateVignetteDTO createOrUpdateVignetteDTO) {
        VignetteDTO updatedVignette = vignetteService.updateVignette(vignetteId, createOrUpdateVignetteDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVignette);
    }

    @GetMapping("/{vignetteId}")
    public ResponseEntity<VignetteDTO> getVignetteById(@PathVariable Long vignetteId) {
        VignetteDTO vignette = vignetteService.getVignetteById(vignetteId);
        return ResponseEntity.status(HttpStatus.OK).body(vignette);
    }

    @GetMapping("/All")
    public ResponseEntity<List<VignetteDTO>> getAllVignettes() {
        List<VignetteDTO> vignettes = vignetteService.getAllVignettes();
        return ResponseEntity.status(HttpStatus.OK).body(vignettes);
    }
}
