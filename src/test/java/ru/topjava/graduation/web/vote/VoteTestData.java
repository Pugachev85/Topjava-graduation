package ru.topjava.graduation.web.vote;

import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.web.MatcherFactory;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.graduation.web.restaurant.RestaurantTestData.RESTAURANT_2;
import static ru.topjava.graduation.web.user.UserTestData.user;
import static ru.topjava.graduation.web.user.UserTestData.user2;

public class VoteTestData {
    public static MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingAssertions(Vote.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("user.registered", "user.password", "user.votes"
                                    , "restaurant.dishes", "restaurant.votes").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int VOTE_ID = 1;
    public static final Vote USER2_VOTE = new Vote(VOTE_ID + 1, LocalDate.now(), RESTAURANT_2, user2);

    public static Vote getUpdated() {
        return new Vote(VOTE_ID, LocalDate.now(), RESTAURANT_2, user);
    }
}
