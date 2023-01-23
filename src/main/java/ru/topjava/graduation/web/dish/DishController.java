package ru.topjava.graduation.web.dish;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.repository.DishRepository;
import ru.topjava.graduation.repository.RestaurantRepository;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.topjava.graduation.util.validation.ValidationUtil.assureIdConsistent;
import static ru.topjava.graduation.util.validation.ValidationUtil.checkNew;

@Tag(
        name = "Admin Dishes Controller",
        description = "allows administrator to manage restaurant dishes ")
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class DishController {
    private final RestaurantRepository restaurantRepository;
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";

    private final DishRepository dishRepository;

    @Operation(
            summary = "Get Dish by id",
            description = "Returns Dish")
    @GetMapping("/{id}")
    public Dish get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get Dish {}", id);
        return dishRepository.checkBelong(restaurantId, id);
    }

    @Transactional
    @Operation(
            summary = "Delete Dish by id",
            description = "Returns status 204")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        int checkedId = dishRepository.checkBelong(restaurantId, id).id();
        log.info("delete {}", checkedId);
        dishRepository.delete(checkedId);
    }

    @Operation(
            summary = "Get all Dishes for Restaurant",
            description = "Returns all Dishes for Restaurant")
    @GetMapping
    public List<Dish> getAllByRestaurant(@PathVariable int restaurantId) {
        log.info("get all dishes by restaurant id {}", restaurantId);
        return dishRepository.getAllByRestaurant(restaurantId);
    }

    @Operation(
            summary = "Get all Dishes for Restaurant on date",
            description = "Returns all Dishes for Restaurant on date")
    @GetMapping("/filter")
    public List<Dish> getAllByRestaurantAndDate(@PathVariable int restaurantId,
                                                @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get all dishes by restaurant id {}", restaurantId);
        return dishRepository.getAllByRestaurantAndDate(restaurantId, date);
    }

    @Transactional
    @Operation(
            summary = "Update Dish by id",
            description = "Returns status 204")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("update dish {}", dish);
        assureIdConsistent(dish, id);
        dishRepository.checkBelong(restaurantId, id);
        dish.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        dishRepository.save(dish);
    }

    @Transactional
    @Operation(
            summary = "Create new Dish for Restaurant",
            description = "Returns Dish with location")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create dish {}", dish);
        checkNew(dish);
        dish.setRestaurant(restaurantRepository.get(restaurantId));
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL.replace("{restaurantId}", String.valueOf(restaurantId))).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
