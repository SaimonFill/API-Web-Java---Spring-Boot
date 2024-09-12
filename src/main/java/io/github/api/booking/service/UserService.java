package io.github.api.booking.service;

import io.github.api.booking.domain.User;
import io.github.api.booking.exception.EmailAlreadyExistsException;
import io.github.api.booking.exception.SearchIdInvalidException;
import io.github.api.booking.exception.SearchInvalidCpfException;
import io.github.api.booking.repository.UserRepository;
import io.github.api.booking.request.UpdateUserRequest;
import io.github.api.booking.request.UserRegisterRequest;
import io.github.api.booking.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public User userRegister(UserRegisterRequest userRegisterRequest) throws Exception {
        userValidator.validateUser(userRegisterRequest.getEmail(), userRegisterRequest.getCpf());

        User user = User.builder()
                .name(userRegisterRequest.getName())
                .email(userRegisterRequest.getEmail())
                .password(userRegisterRequest.getPassword())
                .cpf(userRegisterRequest.getCpf())
                .birthDate(userRegisterRequest.getBirthDate())
                .address(userRegisterRequest.getAddress())
                .build();

        return userRepository.save(user);
    }

    public Page<User> usersList(@PageableDefault Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User searchUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new SearchIdInvalidException("User", id));
    }

    public User searchUserByCpf(String cpf) throws Exception {
        boolean UserCpf = userRepository.existsByCpf(cpf);

        if (!UserCpf) {
            throw new SearchInvalidCpfException(cpf);
        }
        return userRepository.findByCpf(cpf);
    }

    public User updateUser(Long id, UpdateUserRequest updateUserRequest) throws Exception {
        User user = searchUserById(id);
        boolean doubleEmail = userRepository.existsByEmail(updateUserRequest.getEmail());

        if (!user.getEmail().equals(updateUserRequest.getEmail())) {
            if (doubleEmail) {
                throw new EmailAlreadyExistsException(updateUserRequest.getEmail());
            }
            user.setEmail(updateUserRequest.getEmail());
        }
        if (user.getAddress() == null) {
            user.setAddress(updateUserRequest.getAddress());
        }
        if (updateUserRequest.getAddress() != null) {
            user.getAddress().setNeighborhood(updateUserRequest.getAddress().getNeighborhood());
            user.getAddress().setZipCode(updateUserRequest.getAddress().getZipCode());
            user.getAddress().setCity(updateUserRequest.getAddress().getCity());
            user.getAddress().setState(updateUserRequest.getAddress().getState());
            user.getAddress().setPublicPlace(updateUserRequest.getAddress().getPublicPlace());
            user.getAddress().setNumber(updateUserRequest.getAddress().getNumber());
            user.getAddress().setComplement(updateUserRequest.getAddress().getComplement());
        }
        user.setBirthDate(updateUserRequest.getBirthDate());
        user.setName(updateUserRequest.getName());
        user.setPassword(updateUserRequest.getPassword());

        return userRepository.save(user);
    }
}
