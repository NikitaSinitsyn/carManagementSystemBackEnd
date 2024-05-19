package com.carManagment.carManagment.Controller;

import com.carManagment.carManagment.Dto.Repair.CreateOrUpdateRepairDTO;
import com.carManagment.carManagment.Dto.Repair.RepairDTO;
import com.carManagment.carManagment.Service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/repairs")
@Validated
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @PostMapping
    public ResponseEntity<RepairDTO> addRepair(@Valid @RequestBody CreateOrUpdateRepairDTO createOrUpdateRepairDTO) {
        RepairDTO addedRepair = repairService.addRepair(createOrUpdateRepairDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRepair);
    }

    @DeleteMapping("/{repairId}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long repairId) {
        repairService.deleteRepair(repairId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{repairId}")
    public ResponseEntity<RepairDTO> updateRepair(@PathVariable Long repairId,  @RequestBody CreateOrUpdateRepairDTO createOrUpdateRepairDTO) {
        RepairDTO updatedRepair = repairService.updateRepair(repairId, createOrUpdateRepairDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRepair);
    }

    @GetMapping("/{repairId}")
    public ResponseEntity<RepairDTO> getRepairById(@PathVariable Long repairId) {
        RepairDTO repair = repairService.getRepairById(repairId);
        return ResponseEntity.status(HttpStatus.OK).body(repair);
    }

    @GetMapping("/All")
    public ResponseEntity<List<RepairDTO>> getAllRepairs() {
        List<RepairDTO> repairs = repairService.getAllRepairs();
        return ResponseEntity.status(HttpStatus.OK).body(repairs);
    }
}
