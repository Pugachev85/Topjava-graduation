package ru.topjava.graduation.web.vote;

import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.web.MatcherFactory;

import java.time.LocalDate;

import static ru.topjava.graduation.web.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.topjava.graduation.web.user.UserTestData.USER_ID;

public class VoteTestData {
    public static MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);

    public static final int VOTE_ID = 1;
    public static final Vote VOTE_1 = new Vote(VOTE_ID, LocalDate.now(), RESTAURANT1_ID, USER_ID);
    public static final Vote VOTE_2 = new Vote(VOTE_ID + 1, LocalDate.now(), RESTAURANT1_ID + 1, USER_ID + 1);
    public static final Vote VOTE_3 = new Vote(VOTE_ID + 2, LocalDate.now(), RESTAURANT1_ID, USER_ID + 3);
    public static final Vote USER2_VOTE = new Vote(VOTE_ID + 3, LocalDate.now(), RESTAURANT1_ID, USER_ID + 2);

    public static Vote getUpdated() {
        return new Vote(VOTE_ID, LocalDate.now(), RESTAURANT1_ID + 1, USER_ID);
    }
}
