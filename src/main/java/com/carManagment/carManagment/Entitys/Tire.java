package com.carManagment.carManagment.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate purchaseDate;

    @DecimalMin(value = "0.0", message = "Price cannot be less than 0")
    private double price;

    @NotBlank(message = "Model cannot be empty")
    private String supplier;

    @NotNull(message = "Season cannot be empty")
    @Enumerated(EnumType.STRING)
    private Season season;


    private String storageLocation;

    @NotBlank(message = "Size cannot be empty")
    private String size;


    private LocalDate changeDate;

    @ManyToOne
    @JoinTable(
            name = "car_tire",
            joinColumns = @JoinColumn(name = "tire_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id")
    )
    private Car car;

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Tire{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                ", supplier='" + supplier + '\'' +
                ", season=" + season +
                ", storageLocation='" + storageLocation + '\'' +
                ", size='" + size + '\'' +
                ", changeDate=" + changeDate +
                ", carId=" + car.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tire tire = (Tire) o;
        return Double.compare(price, tire.price) == 0 && Objects.equals(id, tire.id) && Objects.equals(purchaseDate, tire.purchaseDate) && Objects.equals(supplier, tire.supplier) && season == tire.season && Objects.equals(storageLocation, tire.storageLocation) && Objects.equals(size, tire.size) && Objects.equals(changeDate, tire.changeDate) && Objects.equals(car, tire.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDate, price, supplier, season, storageLocation, size, changeDate, car);
    }
}
