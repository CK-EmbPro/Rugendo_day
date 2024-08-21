package rw.app.urugendo.day.services.parent.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Bus.dayBus.dto.DayBusDto;
import rw.app.urugendo.day.models.Ticket.Enum.ETicketStatus;
import rw.app.urugendo.day.models.Ticket.dayTIcket.DayTicket;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.*;
import rw.app.urugendo.day.models.Ticket.dayTIcket.utils.DayTicketsMapper;
import rw.app.urugendo.day.models.student.dayStudent.dto.DayStudentDto;
import rw.app.urugendo.day.repositories.tickets.day.DayTicketRepo;
import rw.app.urugendo.day.services.Bus.day.impl.DayBusServiceImpl;
import rw.app.urugendo.day.services.parent.ParentActions;
import rw.app.urugendo.day.services.student.DayStudentService;
import rw.app.urugendo.day.services.ticket.seatImpl.SeatServiceImpl;
import rw.app.urugendo.day.services.usermanagement.impl.UserServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentActionsImpl implements ParentActions {

    private final DayTicketRepo dayTicketRepo;
    private final DayBusServiceImpl busService;
    private final SeatServiceImpl seatService;
    private final UserServiceImpl userService;
    private final DayStudentService studentService;

    @Override

    public synchronized BookedDayTicketDto bookStudentTicket(BookingDayTicketDto toBeBookedTicket) throws ResourceNotFoundException {
        String bookingEmail = userService.getCurrentUser().getParent().getEmail();
        Optional<DayTicket> ticketToBeBooked = dayTicketRepo.findById(toBeBookedTicket.getTicketId());
        if (ticketToBeBooked.isEmpty())
            throw new ResourceNotFoundException("Sorry you can't book the ticket " + toBeBookedTicket.getTicketId() + " because is not available");

        DayBusDto associatedCar = busService.getDayBusByPlateNo(ticketToBeBooked.get().getAssignedCar());
        List<SeatDto> bookedSeats = seatService.getAllSeatsByTicketId(ticketToBeBooked.get().getTicketId());

//        Main booking logic
        if (ticketToBeBooked.get().getTicketStatus() == ETicketStatus.BOOKED || bookedSeats.size() >= associatedCar.getNOfSeats() || ticketToBeBooked.get().getAvailableSeats() <= 0) {
            throw new Error("Ticket is booked already");
        }

        String seatIdentifier = "Seat_" + (bookedSeats.size() + 1);
        CreateSeatDto seatToBeBooked = CreateSeatDto.builder()
                .ticketId(ticketToBeBooked.get().getTicketId())
                .seatIdentifier(seatIdentifier)
                .bookedBy(bookingEmail)
                .bookedTo(toBeBookedTicket.getBookedTo())
                .build();

        SeatDto bookedSeat = seatService.registerTicketSeat(seatToBeBooked);

        if (bookedSeats.size() + 1 == associatedCar.getNOfSeats()) {
            ticketToBeBooked.get().setTicketStatus(ETicketStatus.BOOKED);
        }

        ticketToBeBooked.get().setAvailableSeats(ticketToBeBooked.get().getAvailableSeats() - 1);
//      Main booking logic
        DayTicket alreadyBooked = dayTicketRepo.save(ticketToBeBooked.get());

        BookedDayTicketDto bookedTicket = BookedDayTicketDto.builder()
                .ticketId(alreadyBooked.getTicketId())
                .seatId(bookedSeat.getSeatId())
                .schoolId(alreadyBooked.getSchoolId())
                .bookedTo(bookedSeat.getBookedTo())
                .bookedBy(bookingEmail)
                .departurePoint(alreadyBooked.getDeparturePoint())
                .destinationPoint(alreadyBooked.getDestinationPoint())
                .morningArrivalTime(alreadyBooked.getMorningArrivalTime())
                .morningDepartTime(alreadyBooked.getMorningDepartTime())
                .noonArrivalTime(alreadyBooked.getNoonArrivalTime())
                .noonDepartTime(alreadyBooked.getNoonDepartTime())
                .eveningArrivalTime(alreadyBooked.getEveningArrivalTime())
                .eveningDepartTime(alreadyBooked.getEveningDepartTime())
                .price(alreadyBooked.getPrice())
                .assignedCar(alreadyBooked.getAssignedCar())
                .assignedDriver(alreadyBooked.getAssignedDriver())
                .ticketStatus(alreadyBooked.getTicketStatus())
                .availableSeats(alreadyBooked.getAvailableSeats())
                .build();

        return bookedTicket;


    }

    @Override
    public List<BookedDayTicketDto> viewBookedTickets() throws ResourceNotFoundException {
        String bookingEmail = userService.getCurrentUser().getParent().getEmail();
        List<SeatDto> bookedSeats = seatService.getSeatsByBookedBy(bookingEmail);

        Set<UUID> seatsBookedTo = bookedSeats.stream()
                .map(SeatDto::getBookedTo)
                .collect(Collectors.toSet());

        Set<UUID> ticketIds = bookedSeats.stream()
                .map(SeatDto::getTicketId)
                .collect(Collectors.toSet());

        Set<DayTicket> bookedSeatsTickets = dayTicketRepo.findDayTicketsByTicketId(ticketIds);

        List<BookedDayTicketDto> bookedTickets = new ArrayList<>();
        for (DayTicket bookedTicket : bookedSeatsTickets) {
            List<SeatDto> seats = seatService.getAllSeatsByTicketId(bookedTicket.getTicketId());

            for (SeatDto seat : seats) {
                DayStudentDto student = studentService.getStudentById(seat.getBookedTo());
                if (student == null) continue;

                BookedDayTicketDto bookedDayTicketDto = BookedDayTicketDto.builder()
                        .ticketId(bookedTicket.getTicketId())
                        .schoolId(bookedTicket.getSchoolId())
                        .bookedBy(bookingEmail)
                        .departurePoint(bookedTicket.getDeparturePoint())
                        .destinationPoint(bookedTicket.getDestinationPoint())
                        .morningArrivalTime(bookedTicket.getMorningArrivalTime())
                        .morningDepartTime(bookedTicket.getMorningDepartTime())
                        .noonArrivalTime(bookedTicket.getNoonArrivalTime())
                        .noonDepartTime(bookedTicket.getNoonDepartTime())
                        .eveningArrivalTime(bookedTicket.getEveningArrivalTime())
                        .eveningDepartTime(bookedTicket.getEveningDepartTime())
                        .price(bookedTicket.getPrice())
                        .assignedCar(bookedTicket.getAssignedCar())
                        .assignedDriver(bookedTicket.getAssignedDriver())
                        .ticketStatus(bookedTicket.getTicketStatus())
                        .availableSeats(bookedTicket.getAvailableSeats())
                        .seatId(seat.getSeatId())
                        .bookedTo(student.getStudentId())
                        .build();

                bookedTickets.add(bookedDayTicketDto);
            }
        }

        return bookedTickets;
    }

}
