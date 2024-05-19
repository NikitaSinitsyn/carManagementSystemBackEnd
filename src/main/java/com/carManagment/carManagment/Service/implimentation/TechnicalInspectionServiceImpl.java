package com.carManagment.carManagment.Service.implimentation;

import com.carManagment.carManagment.Dto.TechnicalInspectation.CreateOrUpdateTechnicalInspectionDTO;
import com.carManagment.carManagment.Dto.TechnicalInspectation.TechnicalInspectionDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.TechnicalInspection;
import com.carManagment.carManagment.Entitys.Vignette;
import com.carManagment.carManagment.Exceptions.CarNotFoundException;
import com.carManagment.carManagment.Exceptions.TechnicalInspectionNotFoundException;
import com.carManagment.carManagment.Exceptions.VignetteNotFoundException;
import com.carManagment.carManagment.Mappers.TechnicalInspectionMapper;
import com.carManagment.carManagment.Repository.CarRepository;
import com.carManagment.carManagment.Repository.TechnicalInspectionRepository;
import com.carManagment.carManagment.Service.TechnicalInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnicalInspectionServiceImpl implements TechnicalInspectionService {

    private final TechnicalInspectionRepository technicalInspectionRepository;
    private final TechnicalInspectionMapper technicalInspectionMapper;
    private final CarRepository carRepository;

    @Autowired
    public TechnicalInspectionServiceImpl(TechnicalInspectionRepository technicalInspectionRepository, TechnicalInspectionMapper technicalInspectionMapper, CarRepository carRepository) {
        this.technicalInspectionRepository = technicalInspectionRepository;
        this.technicalInspectionMapper = technicalInspectionMapper;
        this.carRepository = carRepository;
    }

    @Override
    public TechnicalInspectionDTO addTechnicalInspection(CreateOrUpdateTechnicalInspectionDTO createOrUpdateTechnicalInspectionDTO) {
        Car car = null;
        if (createOrUpdateTechnicalInspectionDTO.getCarNumber() != null && !createOrUpdateTechnicalInspectionDTO.getCarNumber().isEmpty()) {
            car = carRepository.findByNumber(createOrUpdateTechnicalInspectionDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateTechnicalInspectionDTO.getCarNumber()));
        }
        TechnicalInspection technicalInspection = new TechnicalInspection();
        technicalInspection.setDatePassed(createOrUpdateTechnicalInspectionDTO.getDatePassed());
        technicalInspection.setDateNextInspection(createOrUpdateTechnicalInspectionDTO.getDateNextInspection());
        technicalInspection.setPrice(createOrUpdateTechnicalInspectionDTO.getPrice());
        technicalInspection.setCar(car);

        // Сохраняем в репозитории
        TechnicalInspection savedTechnicalInspection = technicalInspectionRepository.save(technicalInspection);

        if (car != null) {
            car.setTechnicalInspection(savedTechnicalInspection);
            carRepository.save(car);
        }

        // Преобразуем обратно в DTO и возвращаем
        return technicalInspectionMapper.toDto(savedTechnicalInspection);
    }

    @Override
    public void deleteTechnicalInspection(Long technicalInspectionId) {
        Optional<TechnicalInspection> technicalInspectionOptional = technicalInspectionRepository.findById(technicalInspectionId);
        if (technicalInspectionOptional.isPresent()) {
            TechnicalInspection technicalInspection = technicalInspectionOptional.get();
            if (technicalInspection.getCar() != null) {
                Car car = technicalInspection.getCar();
                car.setTechnicalInspection(null); // Удаляем ссылку на виньетку у машины
                carRepository.save(car); // Сохраняем обновленные данные машины в базу данных
            }
            technicalInspectionRepository.deleteById(technicalInspectionId); // Удаляем виньетку из базы данных
        } else {
            throw new TechnicalInspectionNotFoundException("Technical inspection not found with id: " + technicalInspectionId);
        }

    }

    @Override
    public TechnicalInspectionDTO updateTechnicalInspection(Long technicalInspectionId, CreateOrUpdateTechnicalInspectionDTO createOrUpdateTechnicalInspectionDTO) {

        Optional<TechnicalInspection> optionalTechnicalInspection = technicalInspectionRepository.findById(technicalInspectionId);
        TechnicalInspection existingTechnicalInspection = optionalTechnicalInspection.orElseThrow(() -> new TechnicalInspectionNotFoundException("Technical Inspection not found with id: " + technicalInspectionId));


        // Устанавливаем новые значения
        existingTechnicalInspection.setDatePassed(createOrUpdateTechnicalInspectionDTO.getDatePassed());
        existingTechnicalInspection.setDateNextInspection(createOrUpdateTechnicalInspectionDTO.getDateNextInspection());
        existingTechnicalInspection.setPrice(createOrUpdateTechnicalInspectionDTO.getPrice());

        if (createOrUpdateTechnicalInspectionDTO.getCarNumber() != null && !createOrUpdateTechnicalInspectionDTO.getCarNumber().isEmpty()) {
            Car car = carRepository.findByNumber(createOrUpdateTechnicalInspectionDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateTechnicalInspectionDTO.getCarNumber()));

            car.setTechnicalInspection(existingTechnicalInspection); // Обновляем виньетку у машины
            carRepository.save(car);
            existingTechnicalInspection.setCar(car);

        }

        // Сохраняем обновленный технический осмотр и возвращаем его DTO
        TechnicalInspection updatedTechnicalInspection = technicalInspectionRepository.save(existingTechnicalInspection);
        return technicalInspectionMapper.toDto(updatedTechnicalInspection);
    }

    @Override
    public TechnicalInspectionDTO getTechnicalInspectionById(Long technicalInspectionId) {
        TechnicalInspection technicalInspection = technicalInspectionRepository.findById(technicalInspectionId)
                .orElseThrow(() -> new TechnicalInspectionNotFoundException("Technical Inspection not found with id: " + technicalInspectionId));
        return technicalInspectionMapper.toDto(technicalInspection);
    }

    @Override
    public List<TechnicalInspectionDTO> getAllTechnicalInspections() {
        List<TechnicalInspection> allTechnicalInspections = technicalInspectionRepository.findAll();
        return allTechnicalInspections.stream()
                .map(technicalInspectionMapper::toDto)
                .collect(Collectors.toList());
    }
}