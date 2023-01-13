package ru.topjava.graduation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurant_unique_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(callSuper = true, exclude = {"user"})
public class Restaurant extends NamedEntity {

    @Column(name = "update_date", nullable = false)
    @NotNull
    private LocalDate updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Schema(hidden = true)
    private List<Dish> dishes;
}
