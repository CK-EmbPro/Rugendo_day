package rw.app.urugendo.day.services.ticket.day.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Bus.dayBus.dto.DayBusDto;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto.AssignedDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.RegisteredDriverDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.DayTicket;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.CreateDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.DayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.utils.DayTicketsMapper;
import rw.app.urugendo.day.repositories.tickets.day.DayTicketRepo;
import rw.app.urugendo.day.services.Bus.day.impl.DayBusServiceImpl;
import rw.app.urugendo.day.services.Driver.day.impl.DayDriverServiceImpl;
import rw.app.urugendo.day.services.ticket.day.DayTicketService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayTicketServiceImpl implements DayTicketService {
    private final DayTicketRepo dayTicketRepo;
    private final DayDriverServiceImpl dayDriverService;
    private final DayBusServiceImpl busService;

    @Override
    public DayTicketDto registerDayTicket(CreateDayTicketDto createDayTicketDto) {
        DayTicketDto registereddayTicket = null;
        try {
            RegisteredDriverDto driverToBeAssigned = dayDriverService.getRegisteredDriverByEmail(createDayTicketDto.getAssignedDriver());
            DayBusDto carToBeAssigned = busService.getDayBusByPlateNo(createDayTicketDto.getAssignedCar());
            if (driverToBeAssigned != null && carToBeAssigned != null) {
                DayTicket ticket = DayTicketsMapper.createDayTicketDtoToDayTicket(createDayTicketDto);
                ticket.setAvailableSeats(carToBeAssigned.getNOfSeats());
                DayTicket registeredTicket = dayTicketRepo.save(ticket);
                registereddayTicket = DayTicketsMapper.dayTicketToDayTicketDto(registeredTicket);
            }

        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }


        return registereddayTicket;

    }

    @Override
    public DayTicketDto updateDayTicket(UUID ticketId, DayTicketDto dayTicketDto) {
        DayTicketDto ticketDto = null;
        String email = dayTicketDto.getAssignedDriver();
        String plateNo = dayTicketDto.getAssignedCar();
        try {
            RegisteredDriverDto registeredDriver = dayDriverService.getRegisteredDriverByEmail(email);
            DayBusDto registeredBus = busService.getDayBusByPlateNo(plateNo);

            AssignedDriverDto
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }
    return ticketDto;
    }

    @Override
    public List<DayTicketDto> getAvailableTickets() {
        try {
            List<BoardingTicketDto> availableTickets = boardingTicketRepository.findTicketByTicketStatus(ETicketStatus.AVAILABLE)
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(
                            BoardingTicketsMapper::ticketToTicketDto
                    )
                    .toList();

            return availableTickets;
        }catch (Exception e ){
            log.error("Something bad happened: {}", e.getMessage());
        }

    }

    @Override
    public List<BookedBoardingTicketDto> getBookedTickets() {
        try {
            List<BookedBoardingTicketDto> bookedTickets = boardingTicketRepository.findTicketByTicketStatus(ETicketStatus.BOOKED)
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(
                            BoardingTicketsMapper::ticketToBookedTickedDto
                    )
                    .collect(Collectors.toList());

            return bookedTickets;
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

    }

    @Override
    public boolean deleteTicket(UUID ticketId) {
        try {
            BoardingTicket ticket = boardingTicketRepository.findTicketByTicketId(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + ticketId));

            boardingTicketRepository.delete(ticket);
            return !boardingTicketRepository.existsById(ticketId);
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }


    }

    @Override
    public BoardingTicketDto getSingleTicket(UUID ticketId) {
        try {
            BoardingTicket ticket = boardingTicketRepository.findTicketByTicketId(ticketId)
                    .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + ticketId));

            return BoardingTicketsMapper.ticketToTicketDto(ticket);
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

    }


}
