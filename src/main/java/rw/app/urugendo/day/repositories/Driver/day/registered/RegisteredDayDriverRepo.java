package rw.app.urugendo.day.repositories.Driver.day.registered;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.RegisteredDriver;
import java.util.Optional;
import java.util.UUID;

public interface RegisteredDayDriverRepo extends JpaRepository<RegisteredDriver, UUID> {
    Optional<RegisteredDriver> findRegisteredDriverByEmail(String email);
}
