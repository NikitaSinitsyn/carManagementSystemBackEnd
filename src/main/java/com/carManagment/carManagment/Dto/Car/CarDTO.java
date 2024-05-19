package com.carManagment.carManagment.Dto.Car;

import com.carManagment.carManagment.Dto.Casko.CASKODTO;
import com.carManagment.carManagment.Dto.CivilInsurance.CivilInsuranceDTO;
import com.carManagment.carManagment.Dto.Employee.EmployeeDTO;
import com.carManagment.carManagment.Dto.Repair.RepairDTO;
import com.carManagment.carManagment.Dto.TechnicalInspectation.TechnicalInspectionDTO;
import com.carManagment.carManagment.Dto.Tire.TireDTO;
import com.carManagment.carManagment.Dto.Vignette.VignetteDTO;
import com.carManagment.carManagment.Entitys.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private Long id;
    private String number;
    private String brand;
    private String sap;
    private String inventoryNumber;
    private CarStatus carStatus;
    private BigDecimal salesValue;
    private LocalDate carUsedFromDate;
    private String carUsedToDate;
    private BigDecimal sellingPrice;
    private LocalDate activationDate;
    private LocalDate endDate;
    private BigDecimal amount;
    private int kW;
    private String environmentalCategory;
    private String chassi;
    private int mileage;
    private String city;
    private int numberOfWinterTires;
    private int numberOfSummerTires;
    EmployeeDTO employeeDTO;
    CASKODTO caskoDTO;
    CivilInsuranceDTO civilInsuranceDTO;
    TechnicalInspectionDTO technicalInspectionDTO;
    VignetteDTO vignetteDTO;
    List<RepairDTO> repairDTOList;
    List<TireDTO> summerTireDTOList;
    List<TireDTO> winterTireDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSap() {
        return sap;
    }

    public void setSap(String sap) {
        this.sap = sap;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public BigDecimal getSalesValue() {
        return salesValue;
    }

    public void setSalesValue(BigDecimal salesValue) {
        this.salesValue = salesValue;
    }

    public LocalDate getCarUsedFromDate() {
        return carUsedFromDate;
    }

    public void setCarUsedFromDate(LocalDate carUsedFromDate) {
        this.carUsedFromDate = carUsedFromDate;
    }

    public String getCarUsedToDate() {
        return carUsedToDate;
    }

    public void setCarUsedToDate(String carUsedToDate) {
        this.carUsedToDate = carUsedToDate;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getkW() {
        return kW;
    }

    public void setkW(int kW) {
        this.kW = kW;
    }

    public String getEnvironmentalCategory() {
        return environmentalCategory;
    }

    public void setEnvironmentalCategory(String environmentalCategory) {
        this.environmentalCategory = environmentalCategory;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumberOfWinterTires() {
        return numberOfWinterTires;
    }

    public void setNumberOfWinterTires(int numberOfWinterTires) {
        this.numberOfWinterTires = numberOfWinterTires;
    }

    public int getNumberOfSummerTires() {
        return numberOfSummerTires;
    }

    public void setNumberOfSummerTires(int numberOfSummerTires) {
        this.numberOfSummerTires = numberOfSummerTires;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public CASKODTO getCaskoDTO() {
        return caskoDTO;
    }

    public void setCaskoDTO(CASKODTO caskoDTO) {
        this.caskoDTO = caskoDTO;
    }

    public CivilInsuranceDTO getCivilInsuranceDTO() {
        return civilInsuranceDTO;
    }

    public void setCivilInsuranceDTO(CivilInsuranceDTO civilInsuranceDTO) {
        this.civilInsuranceDTO = civilInsuranceDTO;
    }

    public TechnicalInspectionDTO getTechnicalInspectionDTO() {
        return technicalInspectionDTO;
    }

    public void setTechnicalInspectionDTO(TechnicalInspectionDTO technicalInspectionDTO) {
        this.technicalInspectionDTO = technicalInspectionDTO;
    }

    public VignetteDTO getVignetteDTO() {
        return vignetteDTO;
    }

    public void setVignetteDTO(VignetteDTO vignetteDTO) {
        this.vignetteDTO = vignetteDTO;
    }

    public List<RepairDTO> getRepairDTOList() {
        return repairDTOList;
    }

    public void setRepairDTOList(List<RepairDTO> repairDTOList) {
        this.repairDTOList = repairDTOList;
    }

    public List<TireDTO> getSummerTireDTOList() {
        return summerTireDTOList;
    }

    public void setSummerTireDTOList(List<TireDTO> summerTireDTOList) {
        this.summerTireDTOList = summerTireDTOList;
    }

    public List<TireDTO> getWinterTireDTOList() {
        return winterTireDTOList;
    }

    public void setWinterTireDTOList(List<TireDTO> winterTireDTOList) {
        this.winterTireDTOList = winterTireDTOList;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", brand='" + brand + '\'' +
                ", sap='" + sap + '\'' +
                ", inventoryNumber='" + inventoryNumber + '\'' +
                ", carStatus=" + carStatus +
                ", salesValue=" + salesValue +
                ", carUsedFromDate=" + carUsedFromDate +
                ", carUsedToDate='" + carUsedToDate + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", activationDate=" + activationDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", kW=" + kW +
                ", environmentalCategory='" + environmentalCategory + '\'' +
                ", chassi='" + chassi + '\'' +
                ", mileage=" + mileage +
                ", city='" + city + '\'' +
                ", numberOfWinterTires=" + numberOfWinterTires +
                ", numberOfSummerTires=" + numberOfSummerTires +
                ", employeeDTO=" + employeeDTO +
                ", caskoDTO=" + caskoDTO +
                ", civilInsuranceDTO=" + civilInsuranceDTO +
                ", technicalInspectionDTO=" + technicalInspectionDTO +
                ", vignetteDTO=" + vignetteDTO +
                ", repairDTOList=" + repairDTOList +
                ", summerTireDTOList=" + summerTireDTOList +
                ", winterTireDTOList=" + winterTireDTOList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return kW == carDTO.kW && mileage == carDTO.mileage && numberOfWinterTires == carDTO.numberOfWinterTires && numberOfSummerTires == carDTO.numberOfSummerTires && Objects.equals(id, carDTO.id) && Objects.equals(number, carDTO.number) && Objects.equals(brand, carDTO.brand) && Objects.equals(sap, carDTO.sap) && Objects.equals(inventoryNumber, carDTO.inventoryNumber) && carStatus == carDTO.carStatus && Objects.equals(salesValue, carDTO.salesValue) && Objects.equals(carUsedFromDate, carDTO.carUsedFromDate) && Objects.equals(carUsedToDate, carDTO.carUsedToDate) && Objects.equals(sellingPrice, carDTO.sellingPrice) && Objects.equals(activationDate, carDTO.activationDate) && Objects.equals(endDate, carDTO.endDate) && Objects.equals(amount, carDTO.amount) && Objects.equals(environmentalCategory, carDTO.environmentalCategory) && Objects.equals(chassi, carDTO.chassi) && Objects.equals(city, carDTO.city) && Objects.equals(employeeDTO, carDTO.employeeDTO) && Objects.equals(caskoDTO, carDTO.caskoDTO) && Objects.equals(civilInsuranceDTO, carDTO.civilInsuranceDTO) && Objects.equals(technicalInspectionDTO, carDTO.technicalInspectionDTO) && Objects.equals(vignetteDTO, carDTO.vignetteDTO) && Objects.equals(repairDTOList, carDTO.repairDTOList) && Objects.equals(summerTireDTOList, carDTO.summerTireDTOList) && Objects.equals(winterTireDTOList, carDTO.winterTireDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, brand, sap, inventoryNumber, carStatus, salesValue, carUsedFromDate, carUsedToDate, sellingPrice, activationDate, endDate, amount, kW, environmentalCategory, chassi, mileage, city, numberOfWinterTires, numberOfSummerTires, employeeDTO, caskoDTO, civilInsuranceDTO, technicalInspectionDTO, vignetteDTO, repairDTOList, summerTireDTOList, winterTireDTOList);
    }
}
