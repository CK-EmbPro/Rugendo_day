package rw.app.urugendo.day.services.parent;

import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.BookedDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.BookingDayTicketDto;

import java.util.List;

public interface ParentActions {
    BookedDayTicketDto bookStudentTicket(BookingDayTicketDto ticketDto);
    List<BookedDayTicketDto> viewBookedTickets();

}
