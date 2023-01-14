package ru.topjava.graduation.web.restaurant;

import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.web.MatcherFactory;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "votes");

    public static final int RESTAURANT1_ID = 1;
}
