package rw.app.urugendo.day.services.ticket;

import rw.app.urugendo.models.Ticket.boardingTicket.dto.CreateSeatDto;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.SeatDto;

import java.util.List;
import java.util.UUID;

public interface SeatService {
    SeatDto registerTicketSeat(CreateSeatDto createSeatDto);
    SeatDto updateTicketSeat(SeatDto seatDto, UUID seatId);
    List<SeatDto> getAllSeatsByTicketId(UUID ticketId);
    List<SeatDto> getAllSeats();
    SeatDto getSingleSeat(UUID seatId);
    List<SeatDto> getSeatsByBooked(String bookedBy);
    SeatDto getSeatByTicketId(UUID seatId, UUID ticketId);
    boolean deleteTicketSeat(UUID seatId);
    boolean deleteAllTicketSeats(UUID ticketId);
}
