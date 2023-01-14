package ru.topjava.graduation.web.restaurant;

import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.topjava.graduation.web.dish.DishTestData.DISHES;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "votes");

    public static final int RESTAURANT1_ID = 1;
    public static final String NOTFOUND_ID = "100";


    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT1_ID, "Astoria", LocalDate.now());
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT1_ID + 1, "Prival", LocalDate.now());
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT1_ID + 2, "Ohotnik", LocalDate.now());

    public static final Restaurant RESTAURANT1_WITH_DISHES = new Restaurant(RESTAURANT1_ID, "Astoria", LocalDate.now(),DISHES);

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан", LocalDate.now());
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated", LocalDate.now());
    }
}
