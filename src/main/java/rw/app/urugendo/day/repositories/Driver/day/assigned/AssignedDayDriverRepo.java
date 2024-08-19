package rw.app.urugendo.day.repositories.Driver.day.assigned;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.AssignedDriver;

import java.util.Optional;
import java.util.UUID;

public interface AssignedDayDriverRepo extends JpaRepository<AssignedDriver, UUID> {
    Optional<AssignedDriver> findAssignedDriverByEmail(String email);
    Optional<AssignedDriver> findAssignedDriverByTicketId(UUID ticketId);
}
