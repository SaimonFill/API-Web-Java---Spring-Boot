package io.github.api.booking.validator;

import io.github.api.booking.exception.CpfAlreadyExistsException;
import io.github.api.booking.exception.EmailAlreadyExistsException;
import io.github.api.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateUser(String email, String cpf) throws CpfAlreadyExistsException, EmailAlreadyExistsException {
        boolean doubleEmail = userRepository.existsByEmail(email);
        boolean doubleCpf = userRepository.existsByCpf(cpf);

        if (doubleEmail) {
            throw new EmailAlreadyExistsException(email);
        }
        if (doubleCpf) {
            throw new CpfAlreadyExistsException(cpf);
        }
    }
}
