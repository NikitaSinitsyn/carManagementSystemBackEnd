package com.carManagment.carManagment.Service;

import com.carManagment.carManagment.Dto.Repair.CreateOrUpdateRepairDTO;
import com.carManagment.carManagment.Dto.Repair.RepairDTO;

import java.util.List;

public interface RepairService {
    RepairDTO addRepair(CreateOrUpdateRepairDTO createOrUpdateRepairDTO);
    void deleteRepair(Long repairId);
    RepairDTO updateRepair(Long repairId,CreateOrUpdateRepairDTO createOrUpdateRepairDTO);
    RepairDTO getRepairById(Long repairId);
    List<RepairDTO> getAllRepairs();
}
