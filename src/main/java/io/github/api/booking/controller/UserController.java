package io.github.api.booking.controller;

import io.github.api.booking.domain.User;
import io.github.api.booking.request.UpdateUserRequest;
import io.github.api.booking.request.UserRegisterRequest;
import io.github.api.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> userRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) throws Exception {
        User user = userService.userRegister(userRegisterRequest);
        return ResponseEntity.created(URI.create("/users")).body(user);
    }

    @GetMapping
    public Page<User> usersList(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.usersList(pageable);
    }

    @GetMapping(path = "/{userId}")
    public User searchUserById(@PathVariable Long userId) throws Exception {
        return userService.searchUserById(userId);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public User searchUserByCpf(@PathVariable String cpf) throws Exception {
        return userService.searchUserByCpf(cpf);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable @Valid Long id, @RequestBody @Valid UpdateUserRequest updateUserRequest) throws Exception {
        User user = userService.updateUser(id, updateUserRequest);
        return ResponseEntity.ok().body(user);
    }
}
