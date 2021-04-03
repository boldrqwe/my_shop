package my_shop.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import my_shop.persist.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
