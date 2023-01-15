package ru.topjava.graduation.web.dish;

import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.web.MatcherFactory;

import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int DISH1_ID = 1;

    public static final Dish DISH_1 = new Dish(DISH1_ID, "Борщ Русский", 100.0);
    public static final Dish DISH_2 = new Dish(DISH1_ID + 1, "Суп Харчо", 120.0);
    public static final Dish DISH_3 = new Dish(DISH1_ID + 2, "Рис отварной", 50.0);
    public static final Dish DISH_4 = new Dish(DISH1_ID + 3, "Спагетти", 130.0);
    public static final Dish DISH_5 = new Dish(DISH1_ID + 4, "Гуляш говяжий", 200.0);
    public static final Dish DISH_6 = new Dish(DISH1_ID + 5, "Хлеб", 15.0);
    public static final Dish DISH_7 = new Dish(DISH1_ID + 6, "Чай с лимоном", 60.0);

    public static final Dish DISH_8 = new Dish(DISH1_ID + 7, "Суп гороховый", 100.0);
    public static final Dish DISH_9 = new Dish(DISH1_ID + 8, "Рис отварной", 50.0);
    public static final Dish DISH_10 = new Dish(DISH1_ID + 9, "Спагетти", 130.0);
    public static final Dish DISH_11 = new Dish(DISH1_ID + 10, "Гуляш Свиной", 180.0);
    public static final Dish DISH_12 = new Dish(DISH1_ID + 11, "Хлеб", 15.0);
    public static final Dish DISH_13 = new Dish(DISH1_ID + 12, "Капучино", 100.0);

    public static final Dish DISH_14 = new Dish(DISH1_ID + 13, "Суп \"Весенний\"", 140.0);
    public static final Dish DISH_15 = new Dish(DISH1_ID + 14, "Гречка", 50.0);
    public static final Dish DISH_16 = new Dish(DISH1_ID + 15, "Салат \"Цезарь\"", 130.0);
    public static final Dish DISH_17 = new Dish(DISH1_ID + 16, "Гуляш Свиной", 180.0);
    public static final Dish DISH_18 = new Dish(DISH1_ID + 17, "Хлеб", 15.0);
    public static final Dish DISH_19 = new Dish(DISH1_ID + 18, "Компот", 60.0);

    public static final List<Dish> DISHES = List.of(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7);
    public static final List<Dish> DISHES2 = List.of(DISH_8, DISH_9, DISH_10, DISH_11, DISH_12, DISH_13);
    public static final List<Dish> DISHES3 = List.of(DISH_14, DISH_15, DISH_16, DISH_17, DISH_18, DISH_19);

    public static Dish getNew() {
        return new Dish(null, "Новое блюдо", 1.0);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Новый борщ", 120.0);
    }
}
