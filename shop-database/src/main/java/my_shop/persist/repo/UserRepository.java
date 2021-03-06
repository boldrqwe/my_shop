package my_shop.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import my_shop.persist.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);

    boolean existsUserByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
