package com.carManagment.carManagment.Service;

import com.carManagment.carManagment.Dto.Tire.CreateOrUpdateTireDTO;
import com.carManagment.carManagment.Dto.Tire.TireDTO;
import com.carManagment.carManagment.Entitys.Season;

import java.util.List;

public interface TireService {
    TireDTO addTire(CreateOrUpdateTireDTO createOrUpdateTireDTO);

    void deleteTire(Long tireId);

    TireDTO updateTire(Long tireId, CreateOrUpdateTireDTO createOrUpdateTireDTO);

    TireDTO getTireById(Long tireId);

    List<TireDTO> getAllTires();
    List<TireDTO> getTiresByCarNumberAndSeason(String carNumber, Season season);
    void deleteTiresByCarNumber(String carNumber);
    List<TireDTO> getTiresBySeason(Season season);
    List<TireDTO> updateTiresByCarNumber(String carNumber, List<TireDTO> tireDTOs, Season season);
    void deleteTiresByIds(List<Long> tireIds);




}
