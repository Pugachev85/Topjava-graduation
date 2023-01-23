package ru.topjava.graduation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"vote_date", "user_id"}, name = "vote_unique_idx")})
@Getter
@Setter
@NoArgsConstructor
public class Vote extends BaseEntity {
    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Vote(Integer id, LocalDate date, Integer restaurantId, Integer userId) {
        super(id);
        this.date = date;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public Vote(LocalDate date, Integer restaurantId, Integer userId) {
        this.date = date;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }
}
