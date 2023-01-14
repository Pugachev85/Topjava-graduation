package ru.topjava.graduation.web.dish;

import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.web.MatcherFactory;

import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int DISH1_ID = 1;

    public static final Dish DISH_1 = new Dish(DISH1_ID,"Борщ Русский", 100.0);
    public static final Dish DISH_2 = new Dish(DISH1_ID + 1,"Суп Харчо", 120.0);
    public static final Dish DISH_3 = new Dish(DISH1_ID + 2, "Рис отварной", 50.0);
    public static final Dish DISH_4 = new Dish(DISH1_ID + 3, "Спагетти", 130.0);
    public static final Dish DISH_5 = new Dish(DISH1_ID + 4, "Гуляш говяжий", 200.0);
    public static final Dish DISH_6 = new Dish(DISH1_ID + 5, "Хлеб", 15.0);
    public static final Dish DISH_7 = new Dish(DISH1_ID + 6, "Чай с лимоном", 60.0);

    public static final List<Dish> DISHES = List.of(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7);

    public static Dish getNew() {
        return new Dish(null, "Новое блюдо", 1.0);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID," Новый борщ", 120.0);
    }
}
