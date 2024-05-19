package com.carManagment.carManagment.Service.implimentation;

import com.carManagment.carManagment.Dto.Casko.CASKODTO;
import com.carManagment.carManagment.Dto.Casko.CreateOrUpdateCaskoDTO;
import com.carManagment.carManagment.Entitys.CASKO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Vignette;
import com.carManagment.carManagment.Exceptions.CASKONotFoundException;
import com.carManagment.carManagment.Exceptions.CarNotFoundException;
import com.carManagment.carManagment.Exceptions.VignetteNotFoundException;
import com.carManagment.carManagment.Mappers.CASKOMapper;
import com.carManagment.carManagment.Repository.CASKORepository;
import com.carManagment.carManagment.Repository.CarRepository;
import com.carManagment.carManagment.Service.CASKOService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CASKOServiceImpl implements CASKOService {

    private final CASKORepository caskoRepository;
    private final CASKOMapper caskoMapper;
    private final CarRepository carRepository;

    @Autowired
    public CASKOServiceImpl(CASKORepository caskoRepository, CASKOMapper caskoMapper, CarRepository carRepository) {
        this.caskoRepository = caskoRepository;
        this.caskoMapper = caskoMapper;
        this.carRepository = carRepository;
    }

    @Override
    public CASKODTO addCASKO(CreateOrUpdateCaskoDTO createOrUpdateCaskoDTO) {

        Car car = null;
        if (createOrUpdateCaskoDTO.getCarNumber() != null && !createOrUpdateCaskoDTO.getCarNumber().isEmpty()) {
            car = carRepository.findByNumber(createOrUpdateCaskoDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateCaskoDTO.getCarNumber()));
        }
        CASKO casko = new CASKO();
        casko.setPurchaseDate(createOrUpdateCaskoDTO.getPurchaseDate());
        casko.setExpirationDate(createOrUpdateCaskoDTO.getExpirationDate());
        casko.setPrice(createOrUpdateCaskoDTO.getPrice());
        casko.setCar(car);

        CASKO savedCASKO = caskoRepository.save(casko);

        if (car != null) {
            car.setCasko(savedCASKO);
            carRepository.save(car);
        }
        CASKOMapper caskoMapper = Mappers.getMapper(CASKOMapper.class);

        return caskoMapper.toDto(savedCASKO);
    }

    @Override
    public void deleteCASKO(Long caskoId) {
        Optional<CASKO> caskoOptional = caskoRepository.findById(caskoId);

        if (caskoOptional.isPresent()) {
            CASKO casko = caskoOptional.get();
            if (casko.getCar() != null) {
                Car car = casko.getCar();
                car.setCasko(null); // Удаляем ссылку на виньетку у машины
                carRepository.save(car); // Сохраняем обновленные данные машины в базу данных
            }
            caskoRepository.deleteById(caskoId); // Удаляем виньетку из базы данных
        } else {
            throw new CASKONotFoundException("CASKO not found with id: " + caskoId);
        }

    }

    @Override
    public CASKODTO updateCASKO(Long caskoId, CreateOrUpdateCaskoDTO createOrUpdateCaskoDTO) {

        Optional<CASKO> optionalCasko = caskoRepository.findById(caskoId);
        CASKO existingCASKO = optionalCasko.orElseThrow(() -> new CASKONotFoundException("Casko not found with id: " + caskoId));

        existingCASKO.setPurchaseDate(createOrUpdateCaskoDTO.getPurchaseDate());
        existingCASKO.setExpirationDate(createOrUpdateCaskoDTO.getExpirationDate());
        existingCASKO.setPrice(createOrUpdateCaskoDTO.getPrice());
        if (createOrUpdateCaskoDTO.getCarNumber() != null && !createOrUpdateCaskoDTO.getCarNumber().isEmpty()) {
            Car car = carRepository.findByNumber(createOrUpdateCaskoDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateCaskoDTO.getCarNumber()));

            car.setCasko(existingCASKO);
            carRepository.save(car);
            existingCASKO.setCar(car);
        }

        CASKO updatedCASKO = caskoRepository.save(existingCASKO);
        return caskoMapper.toDto(updatedCASKO);
    }

    @Override
    public CASKODTO getCASKOById(Long caskoId) {
        return caskoRepository.findById(caskoId)
                .map(caskoMapper::toDto)
                .orElseThrow(() -> new CASKONotFoundException("CASKO not found with id: " + caskoId));
    }

    @Override
    public List<CASKODTO> getAllCASKOs() {
        List<CASKO> allCASKOs = caskoRepository.findAll();
        if (allCASKOs.isEmpty()) {
            throw new CASKONotFoundException("No CASKOs found");
        }
        return allCASKOs.stream()
                .map(caskoMapper::toDto)
                .collect(Collectors.toList());
    }
}