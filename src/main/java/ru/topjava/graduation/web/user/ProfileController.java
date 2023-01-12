package ru.topjava.graduation.web.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.to.UserTo;
import ru.topjava.graduation.util.UsersUtil;
import ru.topjava.graduation.web.AuthUser;

import static ru.topjava.graduation.util.validation.ValidationUtil.assureIdConsistent;

@Tag(
        name = "User Profile Controller",
        description = "allows User to manage his profile")
@RestController
@RequestMapping(value = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractUserController {
    static final String REST_URL = "/api/profile";

    @Operation(
            summary = "Get authenticated User profile",
            description = "Returns User")
    @GetMapping
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get {}", authUser);
        return authUser.getUser();
    }

    @Operation(
            summary = "Delete authenticated User his profile",
            description = "Returns status 204")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser) {
        super.delete(authUser.id());
    }

    @Operation(
            summary = "Update authenticated User",
            description = "Returns status 204")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@RequestBody @Valid UserTo userTo, @AuthenticationPrincipal AuthUser authUser) {
        log.info("update {} with id={}", userTo, authUser.id());
        assureIdConsistent(userTo, authUser.id());
        User user = authUser.getUser();
        prepareAndSave(UsersUtil.updateFromTo(user, userTo));
    }

    @Operation(
            summary = "Get User profile with his Votes",
            description = "Returns User profile with his Votes")
    @GetMapping("/with-votes")
    public ResponseEntity<User> getWithVotes(@AuthenticationPrincipal AuthUser authUser) {
        return super.getWithVotes(authUser.id());
    }
}