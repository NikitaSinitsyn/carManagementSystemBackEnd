package com.carManagment.carManagment.Entitys;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String brand;
    private String sap;
    private String inventoryNumber;
    @Enumerated(EnumType.STRING)
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

    @Transient
    private int numberOfWinterTires;

    @Transient
    private int numberOfSummerTires;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private TechnicalInspection technicalInspection;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private CivilInsurance civilInsurance;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private CASKO casko;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private Vignette vignette;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @SQLRestriction("season = 'SUMMER'")
    private List<Tire> summerTires = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @SQLRestriction("season = 'WINTER'")
    private List<Tire> winterTires = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Repair> repairs = new ArrayList<>();

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
        return winterTires.size();
    }

    public void setNumberOfWinterTires(int numberOfWinterTires) {
        this.numberOfWinterTires = numberOfWinterTires;
    }

    public int getNumberOfSummerTires() {
        return summerTires.size();
    }

    public void setNumberOfSummerTires(int numberOfSummerTires) {
        this.numberOfSummerTires = numberOfSummerTires;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TechnicalInspection getTechnicalInspection() {
        return technicalInspection;
    }

    public void setTechnicalInspection(TechnicalInspection technicalInspection) {
        this.technicalInspection = technicalInspection;
    }

    public CivilInsurance getCivilInsurance() {
        return civilInsurance;
    }

    public void setCivilInsurance(CivilInsurance civilInsurance) {
        this.civilInsurance = civilInsurance;
    }

    public CASKO getCasko() {
        return casko;
    }

    public void setCasko(CASKO casko) {
        this.casko = casko;
    }

    public Vignette getVignette() {
        return vignette;
    }

    public void setVignette(Vignette vignette) {
        this.vignette = vignette;
    }

    public List<Tire> getSummerTires() {
        return summerTires;
    }

    public void setSummerTires(List<Tire> summerTires) {
        this.summerTires = summerTires;
    }

    public List<Tire> getWinterTires() {
        return winterTires;
    }

    public void setWinterTires(List<Tire> winterTires) {
        this.winterTires = winterTires;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    @Override
    public String toString() {
        return "Car{" +
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
                ", employee=" + employee +
                ", technicalInspection=" + technicalInspection +
                ", civilInsurance=" + civilInsurance +
                ", casko=" + casko +
                ", vignette=" + vignette +
                ", summerTires=" + summerTires +
                ", winterTires=" + winterTires +
                ", repairs=" + repairs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return kW == car.kW && mileage == car.mileage && numberOfWinterTires == car.numberOfWinterTires && numberOfSummerTires == car.numberOfSummerTires && Objects.equals(id, car.id) && Objects.equals(number, car.number) && Objects.equals(brand, car.brand) && Objects.equals(sap, car.sap) && Objects.equals(inventoryNumber, car.inventoryNumber) && carStatus == car.carStatus && Objects.equals(salesValue, car.salesValue) && Objects.equals(carUsedFromDate, car.carUsedFromDate) && Objects.equals(carUsedToDate, car.carUsedToDate) && Objects.equals(sellingPrice, car.sellingPrice) && Objects.equals(activationDate, car.activationDate) && Objects.equals(endDate, car.endDate) && Objects.equals(amount, car.amount) && Objects.equals(environmentalCategory, car.environmentalCategory) && Objects.equals(chassi, car.chassi) && Objects.equals(city, car.city) && Objects.equals(employee, car.employee) && Objects.equals(technicalInspection, car.technicalInspection) && Objects.equals(civilInsurance, car.civilInsurance) && Objects.equals(casko, car.casko) && Objects.equals(vignette, car.vignette) && Objects.equals(summerTires, car.summerTires) && Objects.equals(winterTires, car.winterTires) && Objects.equals(repairs, car.repairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, brand, sap, inventoryNumber, carStatus, salesValue, carUsedFromDate, carUsedToDate, sellingPrice, activationDate, endDate, amount, kW, environmentalCategory, chassi, mileage, city, numberOfWinterTires, numberOfSummerTires, employee, technicalInspection, civilInsurance, casko, vignette, summerTires, winterTires, repairs);
    }
}
