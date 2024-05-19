package com.carManagment.carManagment.Service;

import com.carManagment.carManagment.Dto.Vignette.CreateOrUpdateVignetteDTO;
import com.carManagment.carManagment.Dto.Vignette.VignetteDTO;

import java.util.List;

public interface VignetteService {

    VignetteDTO addVignette(CreateOrUpdateVignetteDTO createOrUpdateVignetteDTO);

    void deleteVignette(Long vignetteId);

    VignetteDTO updateVignette(Long vignetteId, CreateOrUpdateVignetteDTO createOrUpdateVignetteDTO);

    VignetteDTO getVignetteById(Long vignetteId);

    List<VignetteDTO> getAllVignettes();

}