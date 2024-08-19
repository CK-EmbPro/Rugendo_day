package rw.app.urugendo.day.repositories.tickets;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.app.urugendo.day.models.Ticket.dayTIcket.Seat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeatRepostory extends JpaRepository<Seat, UUID> {
    List<Seat> findByTicketId(UUID ticketId);
    Optional<Seat> findBySeatIdAndTicketId(UUID seatId, UUID ticketId);
    List<Seat> findByBookedBy(String bookedBy);
}
