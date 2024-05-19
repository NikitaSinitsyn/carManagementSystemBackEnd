package com.carManagment.carManagment.Service.implimentation;

import com.carManagment.carManagment.Dto.Vignette.CreateOrUpdateVignetteDTO;
import com.carManagment.carManagment.Dto.Vignette.VignetteDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Vignette;
import com.carManagment.carManagment.Exceptions.CarNotFoundException;
import com.carManagment.carManagment.Exceptions.VignetteNotFoundException;
import com.carManagment.carManagment.Mappers.VignetteMapper;
import com.carManagment.carManagment.Repository.CarRepository;
import com.carManagment.carManagment.Repository.VignetteRepository;
import com.carManagment.carManagment.Service.VignetteService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VignetteServiceImpl implements VignetteService {

    private final VignetteRepository vignetteRepository;
    private final VignetteMapper vignetteMapper;
    private final CarRepository carRepository;

    @Autowired
    public VignetteServiceImpl(VignetteRepository vignetteRepository, VignetteMapper vignetteMapper, CarRepository carRepository) {
        this.vignetteRepository = vignetteRepository;
        this.vignetteMapper = vignetteMapper;
        this.carRepository = carRepository;
    }

    @Override
    public VignetteDTO addVignette(CreateOrUpdateVignetteDTO createOrUpdateVignetteDTO) {
        Car car = null;
        if (createOrUpdateVignetteDTO.getCarNumber() != null && !createOrUpdateVignetteDTO.getCarNumber().isEmpty()) {
            car = carRepository.findByNumber(createOrUpdateVignetteDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateVignetteDTO.getCarNumber()));
        }

        Vignette vignette = new Vignette();
        vignette.setPurchaseDate(createOrUpdateVignetteDTO.getPurchaseDate());
        vignette.setExpirationDate(createOrUpdateVignetteDTO.getExpirationDate());
        vignette.setPrice(createOrUpdateVignetteDTO.getPrice());
        vignette.setCar(car); // Привязываем машину, если она была найдена, или оставляем null

        Vignette savedVignette = vignetteRepository.save(vignette);

        if (car != null) {
            car.setVignette(savedVignette);
            carRepository.save(car);
        }


        VignetteMapper vignetteMapper = Mappers.getMapper(VignetteMapper.class);

        return vignetteMapper.toDto(savedVignette);
    }

    @Override
    public void deleteVignette(Long vignetteId) {
        Optional<Vignette> vignetteOptional = vignetteRepository.findById(vignetteId);
        if (vignetteOptional.isPresent()) {
            Vignette vignette = vignetteOptional.get();
            if (vignette.getCar() != null) {
                Car car = vignette.getCar();
                car.setVignette(null); // Удаляем ссылку на виньетку у машины
                carRepository.save(car); // Сохраняем обновленные данные машины в базу данных
            }
            vignetteRepository.deleteById(vignetteId); // Удаляем виньетку из базы данных
        } else {
            throw new VignetteNotFoundException("Vignette not found with id: " + vignetteId);
        }
    }

    @Override
    public VignetteDTO updateVignette(Long vignetteId, CreateOrUpdateVignetteDTO createOrUpdateVignetteDTO) {
        Optional<Vignette> optionalVignette = vignetteRepository.findById(vignetteId);
        Vignette existingVignette = optionalVignette.orElseThrow(() -> new VignetteNotFoundException("Vignette not found with id: " + vignetteId));

        existingVignette.setPurchaseDate(createOrUpdateVignetteDTO.getPurchaseDate());
        existingVignette.setExpirationDate(createOrUpdateVignetteDTO.getExpirationDate());
        existingVignette.setPrice(createOrUpdateVignetteDTO.getPrice());

        // Проверяем, задан ли номер машины в DTO
        if (createOrUpdateVignetteDTO.getCarNumber() != null && !createOrUpdateVignetteDTO.getCarNumber().isEmpty()) {
            Car car = carRepository.findByNumber(createOrUpdateVignetteDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateVignetteDTO.getCarNumber()));
            car.setVignette(existingVignette); // Обновляем виньетку у машины
            carRepository.save(car);
            existingVignette.setCar(car);
        }

        Vignette updatedVignette = vignetteRepository.save(existingVignette);
        return vignetteMapper.toDto(updatedVignette);
    }

    @Override
    public VignetteDTO getVignetteById(Long vignetteId) {
        return vignetteRepository.findById(vignetteId)
                .map(vignetteMapper::toDto)
                .orElseThrow(() -> new VignetteNotFoundException("Vignette not found with id: " + vignetteId));
    }

    @Override
    public List<VignetteDTO> getAllVignettes() {
        List<Vignette> allVignettes = vignetteRepository.findAll();
        if (allVignettes.isEmpty()) {
            throw new VignetteNotFoundException("No vignettes found");
        }
        return allVignettes.stream()
                .map(vignetteMapper::toDto)
                .collect(Collectors.toList());
    }
}
