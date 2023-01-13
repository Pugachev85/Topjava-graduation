package ru.topjava.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.repository.RestaurantRepository;
import ru.topjava.graduation.repository.VoteRepository;
import ru.topjava.graduation.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantTo> getAllWithVotesByDate(LocalDate now) {
        return restaurantRepository.getAllWithDishesByDate(now).stream()
                .map(RestaurantTo::new)
                .peek(restaurantTo -> restaurantTo.setCountOfVotes(
                        voteRepository.getCountByRestaurantAndDate(restaurantTo.getId(), now)))
                .toList();
    }

    public Restaurant getExisted(int restaurantId) {
        return restaurantRepository.getExisted(restaurantId);
    }

    public Vote findByUserAndDate(User user, LocalDate now) {
        return voteRepository.findByUserAndDate(user, now);
    }

    public void save(Vote vote) {
        voteRepository.save(vote);
    }
}
