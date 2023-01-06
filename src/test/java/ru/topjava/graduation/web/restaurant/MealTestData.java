package ru.topjava.graduation.web.restaurant;

import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.to.MealTo;
import ru.topjava.graduation.web.MatcherFactory;

import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.LocalDateTime.of;

public class MealTestData {
    public static final MatcherFactory.Matcher<Restaurant> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "user");
    public static MatcherFactory.Matcher<MealTo> MEAL_TO_MATCHER = MatcherFactory.usingEqualsComparator(MealTo.class);

    public static final int MEAL1_ID = 1;
    public static final int ADMIN_MEAL_ID = 8;

    public static final Restaurant RESTAURANT_1 = new Restaurant(MEAL1_ID, of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Restaurant RESTAURANT_2 = new Restaurant(MEAL1_ID + 1, of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Restaurant RESTAURANT_3 = new Restaurant(MEAL1_ID + 2, of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Restaurant RESTAURANT_4 = new Restaurant(MEAL1_ID + 3, of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Restaurant RESTAURANT_5 = new Restaurant(MEAL1_ID + 4, of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 500);
    public static final Restaurant RESTAURANT_6 = new Restaurant(MEAL1_ID + 5, of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1000);
    public static final Restaurant RESTAURANT_7 = new Restaurant(MEAL1_ID + 6, of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 510);
    public static final Restaurant ADMIN_RESTAURANT_1 = new Restaurant(ADMIN_MEAL_ID, of(2020, Month.JANUARY, 31, 14, 0), "Админ ланч", 510);
    public static final Restaurant ADMIN_RESTAURANT_2 = new Restaurant(ADMIN_MEAL_ID + 1, of(2020, Month.JANUARY, 31, 21, 0), "Админ ужин", 1500);

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_7, RESTAURANT_6, RESTAURANT_5, RESTAURANT_4, RESTAURANT_3, RESTAURANT_2, RESTAURANT_1);

    public static Restaurant getNew() {
        return new Restaurant(null, of(2020, Month.FEBRUARY, 1, 18, 0), "Созданный ужин", 300);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(MEAL1_ID, RESTAURANT_1.getDateTime().plus(2, ChronoUnit.MINUTES), "Обновленный завтрак", 200);
    }
}
