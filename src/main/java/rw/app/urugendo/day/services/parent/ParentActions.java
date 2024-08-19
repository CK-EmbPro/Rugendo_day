package rw.app.urugendo.day.services.parent;

import rw.app.urugendo.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.BookedBoardingTicketDto;

import java.util.List;

public interface ParentActions {
    BookedBoardingTicketDto bookStudentTicket(BookedBoardingTicketDto ticketDto) throws ResourceNotFoundException;
    List<BookedBoardingTicketDto> viewBookedTickets() throws ResourceNotFoundException;

}
