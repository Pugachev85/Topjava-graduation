package ru.topjava.graduation.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.topjava.graduation.repository.VoteRepository;
import ru.topjava.graduation.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.topjava.graduation.web.restaurant.RestaurantTestData.*;
import static ru.topjava.graduation.web.user.UserTestData.USER2_MAIL;
import static ru.topjava.graduation.web.user.UserTestData.USER_MAIL;
import static ru.topjava.graduation.web.vote.VoteController.REST_URL;
import static ru.topjava.graduation.web.vote.VoteTestData.*;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(RESTAURANT_TO_1, RESTAURANT_TO_2, RESTAURANT_TO_3));
    }

    @Test
    @WithUserDetails(value = USER2_MAIL)
    void vote() throws Exception {
        VoteController.votingStopTime = LocalTime.now().plusMinutes(10);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_2.id())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(voteRepository.get(VOTE_ID + 1), USER2_VOTE);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void changeVote() throws Exception {
        VoteController.votingStopTime = LocalTime.now().plusMinutes(10);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_2.id())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(voteRepository.get(VOTE_ID), VoteTestData.getUpdated());
    }

    @Test
    @WithUserDetails(value = USER2_MAIL)
    void voteAfterStop() throws Exception {
        VoteController.votingStopTime = LocalTime.now().minusMinutes(10);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_2.id())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}