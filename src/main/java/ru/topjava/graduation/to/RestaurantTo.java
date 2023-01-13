package ru.topjava.graduation.to;

import lombok.*;
import ru.topjava.graduation.HasId;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.model.Restaurant;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo implements HasId {
    Integer countOfVotes;
    List<Dish> dishes;

    public RestaurantTo(Restaurant r) {
        this(r.id(), r.getName(), null, r.getDishes());
    }

    public RestaurantTo(Integer id, String name, Integer countOfVotes, List<Dish> dishes) {
        super(id, name);
        this.countOfVotes = countOfVotes;
        this.dishes = dishes;
    }
}
