package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r ORDER BY r.name ASC")
    List<Restaurant> getAll();

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> getWithDishes(int id);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Restaurant r WHERE r.updateDate = :today")
    List<Restaurant> getAllWithDishesByDate(LocalDate today);
}