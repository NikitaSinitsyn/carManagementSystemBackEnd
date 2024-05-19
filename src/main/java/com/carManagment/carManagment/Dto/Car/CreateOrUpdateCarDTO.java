package com.carManagment.carManagment.Dto.Car;

import com.carManagment.carManagment.Entitys.CarStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateCarDTO {

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

    @Override
    public String toString() {
        return "CreateOrUpdateCarDTO{" +
                "number='" + number + '\'' +
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateCarDTO that = (CreateOrUpdateCarDTO) o;
        return kW == that.kW && mileage == that.mileage && Objects.equals(number, that.number) && Objects.equals(brand, that.brand) && Objects.equals(sap, that.sap) && Objects.equals(inventoryNumber, that.inventoryNumber) && carStatus == that.carStatus && Objects.equals(salesValue, that.salesValue) && Objects.equals(carUsedFromDate, that.carUsedFromDate) && Objects.equals(carUsedToDate, that.carUsedToDate) && Objects.equals(sellingPrice, that.sellingPrice) && Objects.equals(activationDate, that.activationDate) && Objects.equals(endDate, that.endDate) && Objects.equals(amount, that.amount) && Objects.equals(environmentalCategory, that.environmentalCategory) && Objects.equals(chassi, that.chassi) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, brand, sap, inventoryNumber, carStatus, salesValue, carUsedFromDate, carUsedToDate, sellingPrice, activationDate, endDate, amount, kW, environmentalCategory, chassi, mileage, city);
    }
}
