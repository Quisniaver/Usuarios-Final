package CrearUsuario.Usuarios.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CrearUsuario.Usuarios.model.User;


@Repository

public interface UserRepository extends JpaRepository<User, Long> {
boolean existsByEmail(String email);
Optional<User> findByEmail(String email);
List<User> findAllByOrderByEmailAsc();
void deleteByEmail(String email);

}


