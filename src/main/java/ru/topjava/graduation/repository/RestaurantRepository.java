package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.error.IllegalRequestDataException;
import ru.topjava.graduation.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r ORDER BY r.id ASC")
    List<Restaurant> getAll();

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> getWithDishes(int id);

    default Restaurant getExistedWithDishes(int id) {
        return getWithDishes(id).orElseThrow(
                () -> new IllegalRequestDataException("Restaurant id=" + id + " not found"));
    }
}