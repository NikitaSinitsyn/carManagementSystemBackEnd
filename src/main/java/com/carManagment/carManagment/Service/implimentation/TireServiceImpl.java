package com.carManagment.carManagment.Service.implimentation;

import com.carManagment.carManagment.Dto.Tire.CreateOrUpdateTireDTO;
import com.carManagment.carManagment.Dto.Tire.TireDTO;
import com.carManagment.carManagment.Entitys.Car;
import com.carManagment.carManagment.Entitys.Repair;
import com.carManagment.carManagment.Entitys.Season;
import com.carManagment.carManagment.Entitys.Tire;
import com.carManagment.carManagment.Exceptions.CarNotFoundException;
import com.carManagment.carManagment.Exceptions.InvalidTireException;
import com.carManagment.carManagment.Exceptions.RepairNotFoundException;
import com.carManagment.carManagment.Exceptions.TireNotFoundException;
import com.carManagment.carManagment.Mappers.EmployeeMapper;
import com.carManagment.carManagment.Mappers.TireMapper;
import com.carManagment.carManagment.Repository.CarRepository;
import com.carManagment.carManagment.Repository.TireRepository;
import com.carManagment.carManagment.Service.TireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TireServiceImpl implements TireService {

    private final TireRepository tireRepository;
    private final TireMapper tireMapper;
    private final CarRepository carRepository;
    private final EmployeeMapper employeeMapper;


    @Autowired
    public TireServiceImpl(TireRepository tireRepository, TireMapper tireMapper, CarRepository carRepository, EmployeeMapper employeeMapper) {
        this.tireRepository = tireRepository;
        this.tireMapper = tireMapper;
        this.carRepository = carRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public TireDTO addTire(CreateOrUpdateTireDTO createOrUpdateTireDTO) {
        Car car = null;
        if (createOrUpdateTireDTO.getCarNumber() != null && !createOrUpdateTireDTO.getCarNumber().isEmpty()) {
            car = carRepository.findByNumber(createOrUpdateTireDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateTireDTO.getCarNumber()));
        }
        Tire tire = new Tire();
        tire.setPurchaseDate(createOrUpdateTireDTO.getPurchaseDate());
        tire.setPrice(createOrUpdateTireDTO.getPrice());
        tire.setSupplier(createOrUpdateTireDTO.getSupplier());
        tire.setSeason(createOrUpdateTireDTO.getSeason());
        tire.setSize(createOrUpdateTireDTO.getSize());
        tire.setStorageLocation(createOrUpdateTireDTO.getStorageLocation());
        tire.setChangeDate(createOrUpdateTireDTO.getChangeDate());

        // Добавление шины в соответствующий список шин в машине
        if (car != null) {
            if (tire.getSeason() == Season.SUMMER) {
                car.getSummerTires().add(tire);
            } else if (tire.getSeason() == Season.WINTER) {
                car.getWinterTires().add(tire);
            }
        }

        // Сохранение шины и возврат в виде TireDTO
        tire.setCar(car);
        Car savedCar = carRepository.save(car);
        return tireMapper.toDto(tire.getSeason() == Season.SUMMER ? savedCar.getSummerTires().get(savedCar.getSummerTires().size() - 1) : savedCar.getWinterTires().get(savedCar.getWinterTires().size() - 1));
    }

    @Override
    public void deleteTire(Long tireId) {
        Optional<Tire> tireOptional = tireRepository.findById(tireId);
        if (tireOptional.isPresent()) {
            Tire tire = tireOptional.get();
            if (tire.getCar() != null) {
                Car car = tire.getCar();
                // Удаляем ссылку на ремонт у машины
                car.getRepairs().remove(tire);
                carRepository.save(car); // Сохраняем обновленные данные машины в базу данных
            }
            tireRepository.deleteById(tireId); // Удаляем ремонт из базы данных
        } else {
            throw new TireNotFoundException("Tire not found with id: " + tireId);
        }

    }

    @Override
    public TireDTO updateTire(Long tireId, CreateOrUpdateTireDTO createOrUpdateTireDTO) {

        Optional<Tire> optionalTire = tireRepository.findById(tireId);
        Tire existingTire = optionalTire.orElseThrow(() -> new TireNotFoundException("Vignette not found with id: " + tireId));

       existingTire.setPurchaseDate(createOrUpdateTireDTO.getPurchaseDate());
       existingTire.setPrice(createOrUpdateTireDTO.getPrice());
       existingTire.setSupplier(createOrUpdateTireDTO.getSupplier());
       existingTire.setSeason(createOrUpdateTireDTO.getSeason());
       existingTire.setSize(createOrUpdateTireDTO.getSize());
       existingTire.setStorageLocation(createOrUpdateTireDTO.getStorageLocation());
       existingTire.setChangeDate(createOrUpdateTireDTO.getChangeDate());

        if (createOrUpdateTireDTO.getCarNumber() != null && !createOrUpdateTireDTO.getCarNumber().isEmpty()) {
            Car car = carRepository.findByNumber(createOrUpdateTireDTO.getCarNumber())
                    .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + createOrUpdateTireDTO.getCarNumber()));
            existingTire.setCar(car);
        }


        Tire updatedTire = tireRepository.save(existingTire);
        return tireMapper.toDto(updatedTire);
    }

    @Override
    public TireDTO getTireById(Long tireId) {
        return tireRepository.findById(tireId)
                .map(tireMapper::toDto)
                .orElseThrow(() -> new TireNotFoundException("Tire not found with id: " + tireId));
    }

    @Override
    public List<TireDTO> getAllTires() {
        List<Tire> allTires = tireRepository.findAll();
        if (allTires.isEmpty()) {
            throw new TireNotFoundException("No tires found");
        }
        return allTires.stream()
                .map(tireMapper::toDto)
                .collect(Collectors.toList());
    }



    @Override
    public List<TireDTO> getTiresByCarNumberAndSeason(String carNumber, Season season) {
        Car car = carRepository.findByNumber(carNumber)
                .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + carNumber));
        List<Tire> tires;
        if (season == Season.SUMMER) {
            tires = car.getSummerTires();
        } else if (season == Season.WINTER) {
            tires = car.getWinterTires();
        } else {
            List<Tire> allTires = new ArrayList<>();
            allTires.addAll(car.getSummerTires());
            allTires.addAll(car.getWinterTires());
            tires = allTires;
        }
        return tires.stream()
                .map(tireMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTiresByCarNumber(String carNumber) {
        Car car = carRepository.findByNumber(carNumber)
                .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + carNumber));
        List<Tire> tires = new ArrayList<>();
        tires.addAll(car.getSummerTires());
        tires.addAll(car.getWinterTires());

        tireRepository.deleteAll(tires);
    }

    @Override
    public List<TireDTO> getTiresBySeason(Season season) {
        List<Tire> allTires = tireRepository.findAll();

        List<Tire> tiresBySeason = allTires.stream()
                .filter(tire -> tire.getSeason() == season)
                .toList();

        return tiresBySeason.stream()
                .map(tireMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TireDTO> updateTiresByCarNumber(String carNumber, List<TireDTO> tireDTOs, Season season) {
        // Находим машину по номеру
        Car car = carRepository.findByNumber(carNumber)
                .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + carNumber));

        // Обновляем информацию о шинах в зависимости от сезона
        List<Tire> updatedTires = new ArrayList<>();
        List<Tire> tiresToUpdate;
        if (season == Season.SUMMER) {
            tiresToUpdate = car.getSummerTires();
        } else if (season == Season.WINTER) {
            tiresToUpdate = car.getWinterTires();
        } else {
            throw new IllegalArgumentException("Invalid season specified");
        }

        for (TireDTO tireDTO : tireDTOs) {
            // Поиск существующей шины по ее идентификатору
            Optional<Tire> optionalTire = tiresToUpdate.stream()
                    .filter(tire -> tire.getId().equals(tireDTO.getId()))
                    .findFirst();

            // Если шина найдена, обновляем ее данные
            if (optionalTire.isPresent()) {
                Tire tire = optionalTire.get();
                tire.setSupplier(tireDTO.getSupplier());
                tire.setPurchaseDate(tireDTO.getPurchaseDate());
                tire.setPrice(tireDTO.getPrice());
                tire.setSize(tireDTO.getSize());
                tire.setChangeDate(tireDTO.getChangeDate());
                // Здесь можно добавить обновление других полей шины
                updatedTires.add(tireRepository.save(tire));
            } else {
                // Если шина не найдена, возможно, нужно бросить исключение или создать новую шину
                // Для примера, бросим исключение InvalidTireException
                throw new InvalidTireException("Tire not found with id: " + tireDTO.getId());
            }
        }

        // Преобразуем обновленные шины в DTO и возвращаем список
        return updatedTires.stream()
                .map(tireMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTiresByIds(List<Long> tireIds) {
        for (Long tireId : tireIds) {
            // Поиск шины по идентификатору
            Optional<Tire> optionalTire = tireRepository.findById(tireId);
            if (optionalTire.isPresent()) {
                // Если шина найдена, удаляем ее из базы данных
                tireRepository.deleteById(tireId);
            } else {
                // Если шина не найдена, возможно, нужно бросить исключение или проигнорировать
                // Для примера, бросим исключение TireNotFoundException
                throw new TireNotFoundException("Tire not found with id: " + tireId);
            }
        }
    }
}