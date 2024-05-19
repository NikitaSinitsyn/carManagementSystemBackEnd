package com.carManagment.carManagment.Dto.Tire;

import com.carManagment.carManagment.Entitys.Season;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateTireDTO {
    private LocalDate purchaseDate;
    private double price;
    private String supplier;
    private Season season;
    private String size;
    private String storageLocation;
    private LocalDate changeDate;
    private String carNumber;

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setModel(String supplier) {
        this.supplier = supplier;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return "CreateOrUpdateTireDTO{" +
                "purchaseDate=" + purchaseDate +
                ", price=" + price +
                ", supplier='" + supplier + '\'' +
                ", season=" + season +
                ", size='" + size + '\'' +
                ", storageLocation='" + storageLocation + '\'' +
                ", changeDate=" + changeDate +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateTireDTO that = (CreateOrUpdateTireDTO) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(supplier, that.supplier) && season == that.season && Objects.equals(size, that.size) && Objects.equals(storageLocation, that.storageLocation) && Objects.equals(changeDate, that.changeDate) && Objects.equals(carNumber, that.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseDate, price, supplier, season, size, storageLocation, changeDate, carNumber);
    }
}
