package ru.topjava.graduation.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.repository.RestaurantRepository;
import ru.topjava.graduation.repository.VoteRepository;
import ru.topjava.graduation.service.VoteService;
import ru.topjava.graduation.to.RestaurantTo;
import ru.topjava.graduation.web.AuthUser;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.topjava.graduation.util.RestaurantUtil.convertToTo;
import static ru.topjava.graduation.util.validation.ValidationUtil.assureIdConsistent;

@Tag(
        name = "Vote Controller",
        description = "allows to authenticated user watch restaurants with menu and count of votes, and do vote")
@Slf4j
@RestController
@RequestMapping(value = ru.topjava.graduation.web.vote.VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/api/voting";
    private final VoteRepository voteRepository;
    private final VoteService voteService;
    private final RestaurantRepository restaurantRepository;

    @Operation(
            summary = "Get all Restaurants with today menu",
            description = "Returns all Restaurants with today menu")
    @GetMapping
    public List<RestaurantTo> getRestaurants() {
        log.info("Get restaurants for voting");
        return convertToTo(restaurantRepository.getRestaurantsOnDate(LocalDate.now()));
    }

    @Operation(
            summary = "Get Restaurant by id",
            description = "Returns Restaurant with dishes")
    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getWithDishes(@PathVariable int restaurantId) {
        log.info("get restaurant with dishes{}", restaurantId);
        return restaurantRepository.getWithDishesOnDate(restaurantId, LocalDate.now());
    }

    @Operation(
            summary = "Do first Vote",
            description = "Returns Vote with location")
    @PostMapping(value = "/restaurants/{restaurantId}")
    @Transactional
    public ResponseEntity<Vote> create(@PathVariable int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        log.info("Do Vote");
        Vote created = voteRepository.save(new Vote(LocalDate.now(), restaurantId, authUser.id()));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/votes-today/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Operation(
            summary = "Get today votes",
            description = "Returns today votes")
    @GetMapping("/votes-today")
    public List<Vote> getAllToday() {
        log.info("get today votes");
        return voteRepository.findAllByDate(LocalDate.now());
    }

    @Operation(
            summary = "Get vote by id",
            description = "Returns vote by id")
    @GetMapping("/votes-today/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get vote by id: {}", id);
        return voteRepository.get(id);
    }

    @Operation(
            summary = "Change vote for authenticated user",
            description = "Returns status 204")
    @PutMapping(value = "/votes-today/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void change(@Valid @RequestBody Vote vote, @PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("Attempt to change vote by user id: {}", authUser.id());
        assureIdConsistent(vote, id);
        voteRepository.checkBelong(vote.id(), authUser.id());
        voteService.update(vote);
    }
}
