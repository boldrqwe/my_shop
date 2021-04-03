package my_shop.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import my_shop.persist.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
