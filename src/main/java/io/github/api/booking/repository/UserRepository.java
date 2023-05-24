package io.github.api.booking.repository;

import io.github.api.booking.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    User findByCpf(String cpf);

    Page<User> findAll(Pageable pageable);
}
