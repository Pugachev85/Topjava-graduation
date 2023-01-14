package ru.topjava.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @Schema(hidden = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> dishes;

    @JsonManagedReference(value = "restaurant_votes")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @Schema(hidden = true)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Vote> votes;

    public Restaurant(Integer id, String name, LocalDate updateDate) {
        super(id, name);
        this.updateDate = updateDate;
    }

    public Restaurant(Integer id, String name, LocalDate updateDate, List<Dish> dishes) {
        super(id, name);
        this.updateDate = updateDate;
        this.dishes = dishes;
    }
}
