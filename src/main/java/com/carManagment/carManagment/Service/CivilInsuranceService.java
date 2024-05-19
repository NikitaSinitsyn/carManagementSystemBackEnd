package com.carManagment.carManagment.Service;

import com.carManagment.carManagment.Dto.CivilInsurance.CivilInsuranceDTO;
import com.carManagment.carManagment.Dto.CivilInsurance.CreateOrUpdateCivilInsuranceDTO;

import java.util.List;

public interface CivilInsuranceService {

    CivilInsuranceDTO addCivilInsurance(CreateOrUpdateCivilInsuranceDTO createOrUpdateCivilInsuranceDTO);
    void deleteCivilInsurance(Long civilInsuranceId);
    CivilInsuranceDTO updateCivilInsurance(Long civilInsuranceId, CreateOrUpdateCivilInsuranceDTO createOrUpdateCivilInsuranceDTO);
    CivilInsuranceDTO getCivilInsuranceById(Long civilInsuranceId);
    List<CivilInsuranceDTO> getAllCivilInsurances();
}
