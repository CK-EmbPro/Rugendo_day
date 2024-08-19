package rw.app.urugendo.day.repositories.tickets.day;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.app.urugendo.day.models.Ticket.dayTIcket.DayTicket;

import java.util.UUID;

public interface DayTicketRepo extends JpaRepository<DayTicket, UUID> {
}
