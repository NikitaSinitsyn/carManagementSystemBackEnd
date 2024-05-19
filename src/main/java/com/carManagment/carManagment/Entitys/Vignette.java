package com.carManagment.carManagment.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vignette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Purchase date cannot be null")
    private LocalDate purchaseDate;

    @NotNull(message = "Expiration date cannot be null")
    private LocalDate expirationDate;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "car_id")
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

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Vignette{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", expirationDate=" + expirationDate +
                ", price=" + price +
                ", carId=" + car.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vignette vignette = (Vignette) o;
        return Objects.equals(id, vignette.id) && Objects.equals(purchaseDate, vignette.purchaseDate) && Objects.equals(expirationDate, vignette.expirationDate) && Objects.equals(price, vignette.price) && Objects.equals(car, vignette.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDate, expirationDate, price, car);
    }
}
