package ru.topjava.graduation.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.topjava.graduation.HasId;
import ru.topjava.graduation.model.Restaurant;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo implements HasId {

    String description;

    public RestaurantTo(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public RestaurantTo(Restaurant r) {
        this(r.getId(), r.getName(), r.getDescription());
    }
}
