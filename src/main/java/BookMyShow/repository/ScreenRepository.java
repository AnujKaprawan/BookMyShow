package BookMyShow.repository;


import BookMyShow.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ScreenRepository extends JpaRepository<Screen, UUID> {
}