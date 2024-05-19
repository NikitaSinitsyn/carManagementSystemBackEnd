package com.carManagment.carManagment.Service.implimentation;

import com.carManagment.carManagment.Dto.Car.CarDTO;
import com.carManagment.carManagment.Dto.Car.CreateOrUpdateCarDTO;
import com.carManagment.carManagment.Dto.Casko.CASKODTO;
import com.carManagment.carManagment.Dto.CivilInsurance.CivilInsuranceDTO;
import com.carManagment.carManagment.Dto.Employee.EmployeeDTO;
import com.carManagment.carManagment.Dto.Repair.RepairDTO;
import com.carManagment.carManagment.Dto.TechnicalInspectation.TechnicalInspectionDTO;
import com.carManagment.carManagment.Dto.Tire.TireDTO;
import com.carManagment.carManagment.Dto.Vignette.VignetteDTO;
import com.carManagment.carManagment.Entitys.*;
import com.carManagment.carManagment.Exceptions.*;
import com.carManagment.carManagment.Mappers.*;
import com.carManagment.carManagment.Repository.*;
import com.carManagment.carManagment.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CASKORepository caskoRepository;
    private final CivilInsuranceRepository civilInsuranceRepository;
    private final RepairRepository repairRepository;
    private final TechnicalInspectionRepository technicalInspectionRepository;
    private final EmployeeRepository employeeRepository;
    private final TireRepository tireRepository;
    private final VignetteRepository vignetteRepository;
    private final CarMapper carMapper;
    private final CASKOMapper caskoMapper;
    private final CivilInsuranceMapper civilInsuranceMapper;
    private final EmployeeMapper employeeMapper;
    private final RepairMapper repairMapper;
    private final TechnicalInspectionMapper technicalInspectionMapper;
    private final TireMapper tireMapper;
    private final VignetteMapper vignetteMapper;


    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          CASKORepository caskoRepository,
                          CivilInsuranceRepository civilInsuranceRepository,
                          RepairRepository repairRepository,
                          TechnicalInspectionRepository technicalInspectionRepository,
                          EmployeeRepository employeeRepository,
                          TireRepository tireRepository,
                          VignetteRepository vignetteRepository,
                          CarMapper carMapper, VignetteMapper vignetteMapper, CASKOMapper caskoMapper, CivilInsuranceMapper civilInsuranceMapper, EmployeeMapper employeeMapper, RepairMapper repairMapper, TechnicalInspectionMapper technicalInspectionMapper, TireMapper tireMapper) {
        this.carRepository = carRepository;
        this.caskoRepository = caskoRepository;
        this.civilInsuranceRepository = civilInsuranceRepository;
        this.repairRepository = repairRepository;
        this.technicalInspectionRepository = technicalInspectionRepository;
        this.employeeRepository = employeeRepository;
        this.tireRepository = tireRepository;
        this.vignetteRepository = vignetteRepository;
        this.carMapper = carMapper;
        this.vignetteMapper = vignetteMapper;
        this.caskoMapper = caskoMapper;
        this.civilInsuranceMapper = civilInsuranceMapper;
        this.employeeMapper = employeeMapper;
        this.repairMapper = repairMapper;
        this.technicalInspectionMapper = technicalInspectionMapper;
        this.tireMapper = tireMapper;
    }

    @Override
    public CarDTO addCar(CreateOrUpdateCarDTO createOrUpdateCarDTO) {


        Car car = new Car();
        car.setNumber(createOrUpdateCarDTO.getNumber());
        car.setBrand(createOrUpdateCarDTO.getBrand());
        car.setSap(createOrUpdateCarDTO.getSap());
        car.setInventoryNumber(createOrUpdateCarDTO.getInventoryNumber());
        car.setCarStatus(createOrUpdateCarDTO.getCarStatus());
        car.setSalesValue(createOrUpdateCarDTO.getSalesValue());
        car.setCarUsedFromDate(createOrUpdateCarDTO.getCarUsedFromDate());
        car.setCarUsedToDate(createOrUpdateCarDTO.getCarUsedToDate());
        car.setSellingPrice(createOrUpdateCarDTO.getSellingPrice());
        car.setActivationDate(createOrUpdateCarDTO.getActivationDate());
        car.setEndDate(createOrUpdateCarDTO.getEndDate());
        car.setAmount(createOrUpdateCarDTO.getAmount());
        car.setkW(createOrUpdateCarDTO.getkW());
        car.setEnvironmentalCategory(createOrUpdateCarDTO.getEnvironmentalCategory());
        car.setChassi(createOrUpdateCarDTO.getChassi());
        car.setMileage(createOrUpdateCarDTO.getMileage());
        car.setCity(createOrUpdateCarDTO.getCity());


        car.setSummerTires(new ArrayList<>());
        car.setWinterTires(new ArrayList<>());

        car.setRepairs(new ArrayList<>());


        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);
    }

    @Override
    public void deleteCar(Long carId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        carOptional.ifPresentOrElse(
                car -> carRepository.deleteById(carId),
                () -> {
                    throw new CarNotFoundException("Car not found with id: " + carId);
                }
        );
    }

    @Override
    public CarDTO updateCar(Long carId, CreateOrUpdateCarDTO createOrUpdateCarDTO) {


        Optional<Car> optionalCar = carRepository.findById(carId);
        Car existingCar = optionalCar.orElseThrow(() -> new CarNotFoundException("Car not found with id: " + carId));

        existingCar.setNumber(createOrUpdateCarDTO.getNumber());
        existingCar.setBrand(createOrUpdateCarDTO.getBrand());
        existingCar.setSap(createOrUpdateCarDTO.getSap());
        existingCar.setInventoryNumber(createOrUpdateCarDTO.getInventoryNumber());
        existingCar.setCarStatus(createOrUpdateCarDTO.getCarStatus());
        existingCar.setSalesValue(createOrUpdateCarDTO.getSalesValue());
        existingCar.setCarUsedFromDate(createOrUpdateCarDTO.getCarUsedFromDate());
        existingCar.setCarUsedToDate(createOrUpdateCarDTO.getCarUsedToDate());
        existingCar.setSellingPrice(createOrUpdateCarDTO.getSellingPrice());
        existingCar.setActivationDate(createOrUpdateCarDTO.getActivationDate());
        existingCar.setEndDate(createOrUpdateCarDTO.getEndDate());
        existingCar.setAmount(createOrUpdateCarDTO.getAmount());
        existingCar.setkW(createOrUpdateCarDTO.getkW());
        existingCar.setEnvironmentalCategory(createOrUpdateCarDTO.getEnvironmentalCategory());
        existingCar.setChassi(createOrUpdateCarDTO.getChassi());
        existingCar.setMileage(createOrUpdateCarDTO.getMileage());
        existingCar.setCity(createOrUpdateCarDTO.getCity());


        Car updatedCar = carRepository.save(existingCar);
        return carMapper.toDto(updatedCar);
    }

    @Override
    public CarDTO getCarById(Long carId) {
        return carRepository.findById(carId)
                .map(carMapper::toDto)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + carId));
    }

    @Override
    public List<CarDTO> getAllCars() {
        List<Car> allCars = carRepository.findAll();
        if (allCars.isEmpty()) {
            throw new CarNotFoundException("No cars found");
        }
        return allCars.stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO findCarByNumber(String carNumber) {
        return carRepository.findByNumber(carNumber)
                .map(carMapper::toDto)
                .orElseThrow(() -> new CarNotFoundException("Car not found with number: " + carNumber));
    }

    public List<Car> findByIds(List<Long> carIds) {
        return carIds.stream()
                .map(carRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarDetails(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        Car car = optionalCar.orElseThrow(() -> new CarNotFoundException("Car not found with id: " + carId));
        CarDTO carDTO = carMapper.toDto(car);


        if (car.getCasko() != null) {
            CASKODTO caskodto = caskoMapper.toDto(car.getCasko());
            carDTO.setCaskoDTO(caskodto);
        }

        if (car.getCivilInsurance() != null) {
            CivilInsuranceDTO civilInsuranceDTO = civilInsuranceMapper.toDto(car.getCivilInsurance());
            carDTO.setCivilInsuranceDTO(civilInsuranceDTO);
        }
        if (car.getTechnicalInspection() != null) {
            TechnicalInspectionDTO technicalInspectionDTO = technicalInspectionMapper.toDto(car.getTechnicalInspection());
            carDTO.setTechnicalInspectionDTO(technicalInspectionDTO);
        }

        if (car.getVignette() != null) {
            VignetteDTO vignetteDTO = vignetteMapper.toDto(car.getVignette());
            carDTO.setVignetteDTO(vignetteDTO);
        }

        if (car.getEmployee() != null) {
            EmployeeDTO employeeDTO = employeeMapper.toDto(car.getEmployee());
            carDTO.setEmployeeDTO(employeeDTO);
        }
        List<RepairDTO> repairDTOs = car.getRepairs().stream()
                .map(repairMapper::toDto)
                .collect(Collectors.toList());
        carDTO.setRepairDTOList(repairDTOs);

// Получение данных о летних шинах
        List<TireDTO> summerTireDTOs = car.getSummerTires().stream()
                .map(tireMapper::toDto)
                .collect(Collectors.toList());
        carDTO.setSummerTireDTOList(summerTireDTOs);

// Получение данных о зимних шинах
        List<TireDTO> winterTireDTOs = car.getWinterTires().stream()
                .map(tireMapper::toDto)
                .collect(Collectors.toList());
        carDTO.setWinterTireDTOList(winterTireDTOs);

        carDTO.setNumberOfSummerTires(car.getSummerTires().size());
        carDTO.setNumberOfWinterTires(car.getWinterTires().size());


        return carDTO;
    }
}