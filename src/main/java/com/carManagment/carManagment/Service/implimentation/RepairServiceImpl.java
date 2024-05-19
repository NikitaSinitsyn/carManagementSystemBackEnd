package com.carManagment.carManagment.Service.implimentation;


import com.carManagment.carManagment.Dto.Repair.CreateOrUpdateRepairDTO;
import com.carManagment.carManagment.Dto.Repair.RepairDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Repair;
import com.carManagment.carManagment.Entitys.Vignette;
import com.carManagment.carManagment.Exceptions.CarNotFoundException;
import com.carManagment.carManagment.Exceptions.RepairNotFoundException;
import com.carManagment.carManagment.Exceptions.VignetteNotFoundException;
import com.carManagment.carManagment.Mappers.RepairMapper;
import com.carManagment.carManagment.Repository.CarRepository;
import com.carManagment.carManagment.Repository.RepairRepository;
import com.carManagment.carManagment.Service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepairServiceImpl implements RepairService {

    private final RepairRepository repairRepository;
    private final RepairMapper repairMapper;
    private final CarRepository carRepository;

    @Autowired
    public RepairServiceImpl(RepairRepository repairRepository, RepairMapper repairMapper, CarRepository carRepository) {
        this.repairRepository = repairRepository;
        this.repairMapper = repairMapper;
        this.carRepository = carRepository;
    }

    @Override
    public RepairDTO addRepair(CreateOrUpdateRepairDTO createOrUpdateRepairDTO) {
        Car car = null;
        if (createOrUpdateRepairDTO.getCarNumber() != null && !createOrUpdateRepairDTO.getCarNumber().isEmpty()) {
            car = carRepository.findByNumber(createOrUpdateRepairDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateRepairDTO.getCarNumber()));
        }

        assert car != null;
        Repair repair = new Repair();
        repair.setPrice(createOrUpdateRepairDTO.getPrice());
        repair.setDescription(createOrUpdateRepairDTO.getDescription());
        repair.setCar(car);

        Repair savedRepair = repairRepository.save(repair);


        car.getRepairs().add(savedRepair);

        return repairMapper.toDto(savedRepair);
    }

    @Override
    public void deleteRepair(Long repairId) {
        Optional<Repair> repairOptional = repairRepository.findById(repairId);
        if (repairOptional.isPresent()) {
            Repair repair = repairOptional.get();
            if (repair.getCar() != null) {
                Car car = repair.getCar();
                // Удаляем ссылку на ремонт у машины
                car.getRepairs().remove(repair);
                carRepository.save(car); // Сохраняем обновленные данные машины в базу данных
            }
            repairRepository.deleteById(repairId); // Удаляем ремонт из базы данных
        } else {
            throw new RepairNotFoundException("Repair not found with id: " + repairId);
        }
    }

    @Override
    public RepairDTO updateRepair(Long repairId, CreateOrUpdateRepairDTO createOrUpdateRepairDTO) {
        Optional<Repair> optionalRepair = repairRepository.findById(repairId);
        Repair existingRepair = optionalRepair.orElseThrow(() -> new RepairNotFoundException("Repair not found with id: " + repairId));

        existingRepair.setPrice(createOrUpdateRepairDTO.getPrice());
        existingRepair.setDescription(createOrUpdateRepairDTO.getDescription());

        // Проверяем, задан ли номер машины в DTO
        if (createOrUpdateRepairDTO.getCarNumber() != null && !createOrUpdateRepairDTO.getCarNumber().isEmpty()) {
            Car car = carRepository.findByNumber(createOrUpdateRepairDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateRepairDTO.getCarNumber()));
            existingRepair.setCar(car); // Обновляем ссылку на машину для ремонта
        }

        Repair updatedRepair = repairRepository.save(existingRepair);
        return repairMapper.toDto(updatedRepair);
    }
    @Override
    public RepairDTO getRepairById(Long repairId) {
        return repairRepository.findById(repairId)
                .map(repairMapper::toDto)
                .orElseThrow(() -> new RepairNotFoundException("Repair not found with id: " + repairId));
    }

    @Override
    public List<RepairDTO> getAllRepairs() {
        List<Repair> allRepairs = repairRepository.findAll();
        if (allRepairs.isEmpty()) {
            throw new RepairNotFoundException("No repairs found");
        }
        return allRepairs.stream()
                .map(repairMapper::toDto)
                .collect(Collectors.toList());
    }
}