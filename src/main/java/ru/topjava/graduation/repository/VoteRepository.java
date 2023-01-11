package ru.topjava.graduation.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.model.Vote;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote>{

    Vote findByUserAndDate(User user, LocalDate today);
}
