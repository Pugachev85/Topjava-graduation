package ru.topjava.graduation.web.dish;

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
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.repository.DishRepository;
import ru.topjava.graduation.service.DishService;

import java.net.URI;
import java.util.List;

import static ru.topjava.graduation.util.validation.ValidationUtil.checkNew;

@Tag(
        name = "Admin Dishes Controller",
        description = "allows administrator to manage restaurant dishes ")
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class DishController {
    static final String REST_URL = "/api/admin/restaurant/{restaurantId}/dishes";

    private final DishRepository dishRepository;

    private final DishService dishService;

    @Operation(
            summary = "Get dish with Id",
            description = "Returns status 204")
    @GetMapping("/{id}")
    public Dish get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get Dish {}", id);
        return dishRepository.checkBelongAndGet(restaurantId, id);
    }

    @Operation(
            summary = "Delete dish with Id",
            description = "Returns status 204")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        int checkedId = dishRepository.checkBelongAndGet(restaurantId, id).id();
        log.info("delete {}", checkedId);
        dishRepository.delete(checkedId);
    }

    @Operation(
            summary = "All dishes for restaurant",
            description = "Returns response with dishes")
    @GetMapping
    public List<Dish> getAllByRestaurant(@PathVariable int restaurantId) {
        log.info("get all dishes of restaurant id {}", restaurantId);
        return dishRepository.getAllByRestaurant(restaurantId);
    }

    @Operation(
            summary = "Update dish with Id",
            description = "Returns status 204")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("update dish {}", dish);
        dish.setId(id);
        dishService.save(dish, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create dish {}", dish);
        checkNew(dish);
        Dish created = dishService.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
