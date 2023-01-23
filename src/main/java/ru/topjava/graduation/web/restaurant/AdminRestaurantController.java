package ru.topjava.graduation.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.repository.RestaurantRepository;
import ru.topjava.graduation.to.RestaurantTo;

import java.net.URI;
import java.util.List;

import static ru.topjava.graduation.util.RestaurantUtil.convertToTo;
import static ru.topjava.graduation.util.validation.ValidationUtil.assureIdConsistent;
import static ru.topjava.graduation.util.validation.ValidationUtil.checkNew;

@Tag(
        name = "Admin Restaurant Controller",
        description = "allows administrator to manage restaurants")
@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminRestaurantController {
    static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantRepository restaurantRepository;

    @Operation(
            summary = "Get Restaurant by id",
            description = "Returns Restaurant")
    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return convertToTo(restaurantRepository.getExisted(id));
    }

    @Operation(
            summary = "Get Restaurant by id",
            description = "Returns Restaurant with dishes")
    @GetMapping("/{id}/with-dishes")
    public Restaurant getWithDishes(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return restaurantRepository.getExistedWithDishes(id);
    }

    @Operation(
            summary = "Delete restaurant by id",
            description = "Returns status 204")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        restaurantRepository.deleteExisted(id);
    }

    @Operation(
            summary = "Get all Restaurants",
            description = "Returns all Restaurants")
    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return convertToTo(restaurantRepository.getAll());
    }

    @Operation(
            summary = "Update Restaurant by id",
            description = "Returns status 204")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant {}", restaurant);
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    @Operation(
            summary = "Create Restaurant",
            description = "Returns Restaurant with location")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        checkNew(restaurant);
        Restaurant created = restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}