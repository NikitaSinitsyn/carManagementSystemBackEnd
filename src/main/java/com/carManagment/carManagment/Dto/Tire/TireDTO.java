package com.carManagment.carManagment.Dto.Tire;

import com.carManagment.carManagment.Entitys.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor

public class TireDTO {
    private Long id;
    private LocalDate purchaseDate;
    private double price;
    private String supplier;
    private Season season;
    private String storageLocation;
    private String size;
    private LocalDate changeDate;
    private Long carId;
    private String carNumber;
    private String name;
    private String position;

    public TireDTO(Long id, LocalDate purchaseDate, double price, String supplier, Season season, String storageLocation, String size, LocalDate changeDate) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.supplier = supplier;
        this.season = season;
        this.storageLocation = storageLocation;
        this.size = size;
        this.changeDate = changeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "TireDTO{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                ", supplier='" + supplier + '\'' +
                ", season=" + season +
                ", storageLocation='" + storageLocation + '\'' +
                ", size='" + size + '\'' +
                ", changeDate=" + changeDate +
                ", carId=" + carId +
                ", carNumber='" + carNumber + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TireDTO tireDTO = (TireDTO) o;
        return Double.compare(price, tireDTO.price) == 0 && Objects.equals(id, tireDTO.id) && Objects.equals(purchaseDate, tireDTO.purchaseDate) && Objects.equals(supplier, tireDTO.supplier) && season == tireDTO.season && Objects.equals(storageLocation, tireDTO.storageLocation) && Objects.equals(size, tireDTO.size) && Objects.equals(changeDate, tireDTO.changeDate) && Objects.equals(carId, tireDTO.carId) && Objects.equals(carNumber, tireDTO.carNumber) && Objects.equals(name, tireDTO.name) && Objects.equals(position, tireDTO.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDate, price, supplier, season, storageLocation, size, changeDate, carId, carNumber, name, position);
    }
}
