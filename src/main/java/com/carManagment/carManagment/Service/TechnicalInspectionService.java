package com.carManagment.carManagment.Service;

import com.carManagment.carManagment.Dto.TechnicalInspectation.CreateOrUpdateTechnicalInspectionDTO;
import com.carManagment.carManagment.Dto.TechnicalInspectation.TechnicalInspectionDTO;

import java.util.List;

public interface TechnicalInspectionService {
    TechnicalInspectionDTO addTechnicalInspection(CreateOrUpdateTechnicalInspectionDTO createOrUpdateTechnicalInspectionDTO);
    void deleteTechnicalInspection(Long technicalInspectionId);
    TechnicalInspectionDTO updateTechnicalInspection(Long technicalInspectionId, CreateOrUpdateTechnicalInspectionDTO createOrUpdateTechnicalInspectionDTO);
    TechnicalInspectionDTO getTechnicalInspectionById(Long technicalInspectionId);
    List<TechnicalInspectionDTO> getAllTechnicalInspections();
}
