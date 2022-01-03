package io.github.api.reservas.repository;

import io.github.api.reservas.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Usuario findByCpf(String cpf);

    Page<Usuario> findAll(Pageable pageable);
}
