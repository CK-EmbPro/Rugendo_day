package rw.app.urugendo.day.services.ticket.day;

import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.BookedDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.CreateDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.DayTicketDto;

import java.util.List;
import java.util.UUID;

public interface DayTicketService {
    DayTicketDto registerDayTicket(CreateDayTicketDto createDayTicketDto);
    DayTicketDto updateDayTicket(UUID dayTicketId, DayTicketDto dayTicketDto);
    List<DayTicketDto> getAvailableDayTickets();
    List<BookedDayTicketDto> getBookedDayTickets();
    boolean deleteDayTicket(UUID dayTicketId);
    DayTicketDto getSingleDayTicket(UUID dayTicketId);

}
