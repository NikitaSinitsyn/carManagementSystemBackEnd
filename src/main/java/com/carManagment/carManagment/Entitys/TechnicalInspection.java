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
public class TechnicalInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date Passed cannot be null")
    private LocalDate datePassed;

    @NotNull(message = "Date Next Inspection cannot be null")
    private LocalDate dateNextInspection;
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

    public LocalDate getDatePassed() {
        return datePassed;
    }

    public void setDatePassed(LocalDate datePassed) {
        this.datePassed = datePassed;
    }

    public LocalDate getDateNextInspection() {
        return dateNextInspection;
    }

    public void setDateNextInspection(LocalDate dateNextInspection) {
        this.dateNextInspection = dateNextInspection;
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
        return "TechnicalInspection{" +
                "id=" + id +
                ", datePassed=" + datePassed +
                ", dateNextInspection=" + dateNextInspection +
                ", price=" + price +
                ", carId=" + car.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicalInspection that = (TechnicalInspection) o;
        return Objects.equals(id, that.id) && Objects.equals(datePassed, that.datePassed) && Objects.equals(dateNextInspection, that.dateNextInspection) && Objects.equals(price, that.price) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datePassed, dateNextInspection, price, car);
    }
}
