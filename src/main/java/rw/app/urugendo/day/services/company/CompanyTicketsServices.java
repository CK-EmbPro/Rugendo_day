package rw.app.urugendo.day.services.company;

import rw.app.urugendo.models.Ticket.boardingTicket.dto.BoardingTicketDto;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.BookedBoardingTicketDto;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.CreateBoardingTicketDto;

import java.util.List;
import java.util.UUID;

public interface CompanyTicketsServices {
    List<BookedBoardingTicketDto> bookedTicketsOnCompany(UUID companyId);
    List<BoardingTicketDto> availableTicketsOnCompany(UUID companyId);
    BoardingTicketDto registerTicket(CreateBoardingTicketDto createBoardingTicketDto);


}
