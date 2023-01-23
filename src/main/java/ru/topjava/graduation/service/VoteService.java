package ru.topjava.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.topjava.graduation.error.DataConflictException;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.repository.VoteRepository;

import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {
    public static LocalTime votingStopTime = LocalTime.of(11, 0);// 11:00 The time of stopping voting today
    private final VoteRepository voteRepository;

    public void update(Vote vote) {
        if (LocalTime.now().isAfter(votingStopTime)) {
            throw new DataConflictException("You can`t change your vote after 11:00");
        }
        voteRepository.save(vote);
    }
}
