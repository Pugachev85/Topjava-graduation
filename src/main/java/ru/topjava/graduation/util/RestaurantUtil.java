package ru.topjava.graduation.util;

import lombok.experimental.UtilityClass;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.to.RestaurantTo;

import java.util.List;


@UtilityClass
public class RestaurantUtil {
    public static List<RestaurantTo> convertToTo(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantTo::new)
                .toList();
    }

    public static RestaurantTo convertToTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant);
    }
}
