package ru.topjava.graduation.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.error.IllegalRequestDataException;
import ru.topjava.graduation.model.Dish;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d")
    List<Dish> getAll();

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId and d.id = :id ")
    Optional<Dish> get(int restaurantId, int id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> getAllByRestaurant(int restaurantId);

    default Dish checkBelongAndGet(int restaurantId, int id) {
        return get(restaurantId, id).orElseThrow(
                () -> new IllegalRequestDataException("Dish id=" + id + " doesn't belong to Restaurant id=" + restaurantId));
    }
}