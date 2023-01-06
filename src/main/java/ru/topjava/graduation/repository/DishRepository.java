package ru.topjava.graduation.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Dish;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restId")
    List<Dish> getAll(int restId);
    

    @Query("SELECT d FROM Dish d WHERE d.id = :id")
    Optional<Dish> get(int id);
}