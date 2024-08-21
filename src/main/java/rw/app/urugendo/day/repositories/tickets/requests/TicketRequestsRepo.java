package rw.app.urugendo.day.repositories.tickets.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.RequestedTicketRoute;

import java.util.List;
import java.util.UUID;

public interface TicketRequestsRepo extends JpaRepository<RequestedTicketRoute, UUID> {
    List<RequestedTicketRoute> findRequestedTicketRoutesByRequestedBy(String requestedBy);
    List<RequestedTicketRoute> findRequestedTicketRoutesBySchoolId(UUID schoolId);
}
