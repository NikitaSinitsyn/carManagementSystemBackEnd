package com.carManagment.carManagment.Service.implimentation;

import com.carManagment.carManagment.Dto.CivilInsurance.CivilInsuranceDTO;
import com.carManagment.carManagment.Dto.CivilInsurance.CreateOrUpdateCivilInsuranceDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.CivilInsurance;
import com.carManagment.carManagment.Entitys.Vignette;
import com.carManagment.carManagment.Exceptions.CarNotFoundException;
import com.carManagment.carManagment.Exceptions.CivilInsuranceNotFoundException;
import com.carManagment.carManagment.Exceptions.VignetteNotFoundException;
import com.carManagment.carManagment.Mappers.CarMapper;
import com.carManagment.carManagment.Mappers.CivilInsuranceMapper;
import com.carManagment.carManagment.Mappers.EmployeeMapper;
import com.carManagment.carManagment.Repository.CarRepository;
import com.carManagment.carManagment.Repository.CivilInsuranceRepository;
import com.carManagment.carManagment.Service.CivilInsuranceService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CivilInsuranceServiceImpl implements CivilInsuranceService {

    private final CivilInsuranceRepository civilInsuranceRepository;
    private final CivilInsuranceMapper civilInsuranceMapper;
    private final CarRepository carRepository;

    @Autowired
    public CivilInsuranceServiceImpl(CivilInsuranceRepository civilInsuranceRepository, CivilInsuranceMapper civilInsuranceMapper, CarMapper carMapper, CarRepository carRepository, EmployeeMapper employeeMapper) {
        this.civilInsuranceRepository = civilInsuranceRepository;
        this.civilInsuranceMapper = civilInsuranceMapper;
        this.carRepository = carRepository;
    }

    @Override
    public CivilInsuranceDTO addCivilInsurance(CreateOrUpdateCivilInsuranceDTO createOrUpdateCivilInsuranceDTO) {
        Car car = null;
        if (createOrUpdateCivilInsuranceDTO.getCarNumber() != null && !createOrUpdateCivilInsuranceDTO.getCarNumber().isEmpty()) {
            car = carRepository.findByNumber(createOrUpdateCivilInsuranceDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateCivilInsuranceDTO.getCarNumber()));
        }
        CivilInsurance civilInsurance = new CivilInsurance();
        civilInsurance.setPurchaseDate(createOrUpdateCivilInsuranceDTO.getPurchaseDate());
        civilInsurance.setExpirationDate(createOrUpdateCivilInsuranceDTO.getExpirationDate());
        civilInsurance.setPrice(createOrUpdateCivilInsuranceDTO.getPrice());
        // Устанавливаем найденный объект Car в CivilInsurance
        civilInsurance.setCar(car);

        // Сохраняем в репозитории
        CivilInsurance savedCivilInsurance = civilInsuranceRepository.save(civilInsurance);

        if (car != null) {
            car.setCivilInsurance(civilInsurance);
            carRepository.save(car);

        }

        // Преобразуем обратно в DTO и возвращаем, добавив информацию о сотруднике и департаменте
        CivilInsuranceMapper civilInsuranceMapper = Mappers.getMapper(CivilInsuranceMapper.class);

        return civilInsuranceMapper.toDto(savedCivilInsurance);
    }

    @Override
    public void deleteCivilInsurance(Long civilInsuranceId) {
        Optional<CivilInsurance> civilInsuranceOptional = civilInsuranceRepository.findById(civilInsuranceId);

        if (civilInsuranceOptional.isPresent()) {
            CivilInsurance civilInsurance = civilInsuranceOptional.get();
            if (civilInsurance.getCar() != null) {
                Car car = civilInsurance.getCar();
                car.setCivilInsurance(null); // Удаляем ссылку на виньетку у машины
                carRepository.save(car); // Сохраняем обновленные данные машины в базу данных
            }
            civilInsuranceRepository.deleteById(civilInsuranceId); // Удаляем виньетку из базы данных
        } else {
            throw new CivilInsuranceNotFoundException("Civil insurance not found with id: " + civilInsuranceId);
        }

    }

    @Override
    public CivilInsuranceDTO updateCivilInsurance(Long civilInsuranceId, CreateOrUpdateCivilInsuranceDTO createOrUpdateCivilInsuranceDTO) {

        Optional<CivilInsurance> optionalCivilInsurance = civilInsuranceRepository.findById(civilInsuranceId);

        CivilInsurance existingCivilInsurance = optionalCivilInsurance.orElseThrow(() -> new CivilInsuranceNotFoundException("Civil insurance not found with id: " + civilInsuranceId));


        existingCivilInsurance.setPurchaseDate(createOrUpdateCivilInsuranceDTO.getPurchaseDate());
        existingCivilInsurance.setExpirationDate(createOrUpdateCivilInsuranceDTO.getExpirationDate());
        existingCivilInsurance.setPrice(createOrUpdateCivilInsuranceDTO.getPrice());

        if (createOrUpdateCivilInsuranceDTO.getCarNumber() != null && !createOrUpdateCivilInsuranceDTO.getCarNumber().isEmpty()) {
            Car car = carRepository.findByNumber(createOrUpdateCivilInsuranceDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateCivilInsuranceDTO.getCarNumber()));

            car.setCivilInsurance(existingCivilInsurance); // Обновляем виньетку у машины
            carRepository.save(car);
            existingCivilInsurance.setCar(car);
        }

        CivilInsurance updatedCivilInsurance = civilInsuranceRepository.save(existingCivilInsurance);
        return civilInsuranceMapper.toDto(updatedCivilInsurance);
    }

    @Override
    public CivilInsuranceDTO getCivilInsuranceById(Long civilInsuranceId) {
        return civilInsuranceRepository.findById(civilInsuranceId)
                .map(civilInsuranceMapper::toDto)
                .orElseThrow(() -> new CivilInsuranceNotFoundException("Civil insurance not found with id: " + civilInsuranceId));
    }

    @Override
    public List<CivilInsuranceDTO> getAllCivilInsurances() {
        List<CivilInsurance> allCivilInsurances = civilInsuranceRepository.findAll();
        if (allCivilInsurances.isEmpty()) {
            throw new CivilInsuranceNotFoundException("No civil insurances found");
        }
        return allCivilInsurances.stream()
                .map(civilInsuranceMapper::toDto)
                .collect(Collectors.toList());
    }
}
