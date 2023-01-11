package ru.topjava.graduation.web.vote;


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

@Slf4j
@RestController
@RequestMapping(value = ru.topjava.graduation.web.vote.VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/voting";

    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantRepository.getAllWithVotes(LocalDate.now());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void vote(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("add vote");
        if(LocalTime.now().isAfter(LocalTime.of(00, 10))) throw new DataConflictException("Voting Blocked after 11:00");
        User user = authUser.getUser();
        Restaurant restaurant = restaurantRepository.getExisted(id);
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
