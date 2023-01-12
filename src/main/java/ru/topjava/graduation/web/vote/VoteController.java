package ru.topjava.graduation.web.vote;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.topjava.graduation.error.AppException;
import ru.topjava.graduation.error.DataConflictException;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.repository.RestaurantRepository;
import ru.topjava.graduation.repository.VoteRepository;
import ru.topjava.graduation.util.UsersUtil;
import ru.topjava.graduation.web.AuthUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Tag(
        name = "Vote Controller",
        description = "allows to authenticated user watch restaurants with menu and count of votes, and do vote")
@Slf4j
@RestController
@RequestMapping(value = ru.topjava.graduation.web.vote.VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/voting";

    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    @Operation(
            summary = "Get restaurants with menu and count of votes",
            description = "Returns restaurants with menu and count of votes")
    @GetMapping
    public List<Restaurant> getAll() { //TODO: change Restaurant to RestaurantTO (updateDate, countOfVotes, Dishes)
        log.info("getAll");
        return restaurantRepository.getAllWithVotes(LocalDate.now());
    }

    @Operation(
            summary = "Do vote for authenticated user",
            description = "Returns status 204")
    @PatchMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void vote(@PathVariable int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        log.info("Attempt to vote by user id: {}", authUser.id());
        // 11:00 The time of stopping voting today
        if(LocalTime.now().isAfter(LocalTime.of(11, 00))) {
            throw new DataConflictException("Voting Blocked after 11:00");
        }
        User user = authUser.getUser();
        Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
        Vote vote = voteRepository.findByUserAndDate(user, LocalDate.now());
        if (vote == null){
            vote = new Vote();
            vote.setUser(user);
            vote.setDate(LocalDate.now());
        }
        vote.setRestaurant(restaurant);
        voteRepository.save(vote);
    }
}
