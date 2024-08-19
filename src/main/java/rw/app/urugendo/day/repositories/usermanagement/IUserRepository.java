package rw.app.urugendo.day.repositories.usermanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.app.urugendo.day.models.usermanagement.User;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findFirstByEmail(String email);
    User findByEmail(String email);

}
