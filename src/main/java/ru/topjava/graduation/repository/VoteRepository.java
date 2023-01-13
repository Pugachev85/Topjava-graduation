package ru.topjava.graduation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.model.Vote;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote>{

    Vote findByUserAndDate(User user, LocalDate today);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.date = :now AND v.user.id = :id ")
    Integer getCountByRestaurantAndDate(Integer id, LocalDate now);
}
