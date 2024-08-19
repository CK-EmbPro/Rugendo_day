package rw.app.urugendo.day.repositories.tickets.day;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.app.urugendo.day.models.Ticket.Enum.ETicketStatus;
import rw.app.urugendo.day.models.Ticket.dayTIcket.DayTicket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DayTicketRepo extends JpaRepository<DayTicket, UUID> {
    List<Optional<DayTicket>> findDayTicketsByTicketStatus(ETicketStatus ticketStatus);
}
