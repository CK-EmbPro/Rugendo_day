package rw.app.urugendo.day.repositories.tickets.day;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.app.urugendo.day.models.Ticket.Enum.ETicketStatus;
import rw.app.urugendo.day.models.Ticket.dayTIcket.DayTicket;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface DayTicketRepo extends JpaRepository<DayTicket, UUID> {
    List<Optional<DayTicket>> findDayTicketsByTicketStatus(ETicketStatus ticketStatus);

    @Query("SELECT t FROM DayTicket t WHERE t.ticketId IN :ticketIds")
    Set<DayTicket> findDayTicketsByTicketId(Set<UUID> ticketIds);
}
