package ru.topjava.graduation.web.vote;

import ru.topjava.graduation.model.Vote;

import java.time.LocalDate;

import static ru.topjava.graduation.web.restaurant.RestaurantTestData.RESTAURANT_1;
import static ru.topjava.graduation.web.user.UserTestData.user;

public class VoteTestData {
    public static final Vote USER_VOTE = new Vote(1,LocalDate.now(),RESTAURANT_1,user);
}
