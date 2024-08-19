package rw.app.urugendo.day.services.company.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.app.urugendo.models.Ticket.Enum.ETicketStatus;
import rw.app.urugendo.models.Ticket.boardingTicket.BoardingTicket;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.BoardingTicketDto;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.BookedBoardingTicketDto;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.CreateBoardingTicketDto;
import rw.app.urugendo.models.Ticket.boardingTicket.utils.BoardingTicketsMapper;
import rw.app.urugendo.repositories.tickets.boarding.BoardingTicketRepository;
import rw.app.urugendo.services.company.CompanyTicketsServices;
import rw.app.urugendo.services.ticket.day.impl.DayTicketServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyTicketsServiceImpl implements CompanyTicketsServices {
    private final BoardingTicketRepository boardingTicketRepository;
    private final DayTicketServiceImpl ticketService;
    @Override
    public List<BookedBoardingTicketDto> bookedTicketsOnCompany(UUID companyId) {
        List<BoardingTicket> bookedTickets = boardingTicketRepository.findTicketsByTicketStatusAndCompanyId(ETicketStatus.BOOKED, companyId);
        return bookedTickets
                .stream()
                .map(
                        BoardingTicketsMapper::ticketToBookedTickedDto
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardingTicketDto> availableTicketsOnCompany(UUID companyId) {
        List<BoardingTicket> availableTickets = boardingTicketRepository.findTicketsByTicketStatusAndCompanyId(ETicketStatus.AVAILABLE, companyId);
        return availableTickets
                .stream()
                .map(
                        BoardingTicketsMapper::ticketToTicketDto
                )
                .collect(Collectors.toList());
    }

    @Override
    public BoardingTicketDto registerTicket(CreateBoardingTicketDto createBoardingTicketDto) {
        return ticketService.registerTicket(createBoardingTicketDto);
    }


}
