package com.carManagment.carManagment.Service;

import com.carManagment.carManagment.Dto.Casko.CASKODTO;
import com.carManagment.carManagment.Dto.Casko.CreateOrUpdateCaskoDTO;

import java.util.List;

public interface CASKOService {
    CASKODTO addCASKO(CreateOrUpdateCaskoDTO createOrUpdateCaskoDTO);
    void deleteCASKO(Long caskoId);
    CASKODTO updateCASKO(Long caskoId, CreateOrUpdateCaskoDTO createOrUpdateCaskoDTO);
    CASKODTO getCASKOById(Long caskoId);
    List<CASKODTO> getAllCASKOs();
}