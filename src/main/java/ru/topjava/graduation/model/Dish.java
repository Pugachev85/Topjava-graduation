package ru.topjava.graduation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends NamedEntity {

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "price", nullable = false)
    @NotNull
    @Min(value = 1)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @Schema(hidden = true)
    private Restaurant restaurant;

    public Dish(Integer id, String name, LocalDate date, Double price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }
}