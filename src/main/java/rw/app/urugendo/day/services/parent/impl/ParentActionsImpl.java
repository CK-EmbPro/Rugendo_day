package rw.app.urugendo.day.services.parent.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.app.urugendo.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.models.Bus.boardingBus.dto.BusDto;
import rw.app.urugendo.models.Ticket.Enum.ETicketStatus;
import rw.app.urugendo.models.Ticket.boardingTicket.BoardingTicket;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.BoardingTicketDto;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.BookedBoardingTicketDto;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.CreateSeatDto;
import rw.app.urugendo.models.Ticket.boardingTicket.dto.SeatDto;
import rw.app.urugendo.models.Ticket.boardingTicket.utils.BoardingTicketsMapper;
import rw.app.urugendo.models.student.boardingStudent.dto.BoardingStudentDto;
import rw.app.urugendo.models.student.boardingStudent.dto.CreateBoardingStudentDto;
import rw.app.urugendo.repositories.tickets.boarding.BoardingTicketRepository;
import rw.app.urugendo.services.Bus.boarding.impl.BoardingBusServiceImpl;
import rw.app.urugendo.services.nesaReports.impl.StudentServiceImpl;
import rw.app.urugendo.services.parent.ParentActions;
import rw.app.urugendo.services.ticket.seatImpl.SeatServiceImpl;
import rw.app.urugendo.services.usermanagement.impl.UserServiceImpl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentActionsImpl implements ParentActions {

    private final BoardingTicketRepository boardingTicketRepository;
    private final StudentServiceImpl studentService;
    private final BoardingBusServiceImpl busService;
    private final SeatServiceImpl seatService;
    private final UserServiceImpl userService;

    @Override

    public synchronized BookedBoardingTicketDto bookStudentTicket(BookedBoardingTicketDto toBebookedBoardingTicketDto) throws ResourceNotFoundException {
        String bookingEmail = userService.getCurrentUser().getParent().getEmail();
        BoardingTicket ticketToBeBooked = boardingTicketRepository.findTicketByTicketId(toBebookedBoardingTicketDto.getTicketId())
                .orElseThrow(() -> new EntityNotFoundException("Sorry you can't book the ticket " + toBebookedBoardingTicketDto.getTicketId() + " because is not available"));

        BusDto associatedCar = busService.getBusByPlateNo(ticketToBeBooked.getAssignedCar());
        List<SeatDto> bookedSeats = seatService.getAllSeatsByTicketId(ticketToBeBooked.getTicketId());

//        Main booking logic
        if (ticketToBeBooked.getTicketStatus() == ETicketStatus.BOOKED || bookedSeats.size() >= associatedCar.getNOfSeats() || ticketToBeBooked.getAvailableSeats() <= 0) {
            throw new Error("Ticket is booked already");
        }

        String seatIdentifier = "Seat_" + (bookedSeats.size() + 1);
        CreateSeatDto seatToBeBooked = CreateSeatDto.builder()
                .ticketId(ticketToBeBooked.getTicketId())
                .seatIdentifier(seatIdentifier)
                .bookedBy(bookingEmail)
                .build();

        SeatDto bookedSeat = seatService.registerTicketSeat(seatToBeBooked);

        if (bookedSeats.size() + 1 == associatedCar.getNOfSeats()) {
            ticketToBeBooked.setTicketStatus(ETicketStatus.BOOKED);
        }

        ticketToBeBooked.setAvailableSeats(ticketToBeBooked.getAvailableSeats() - 1);
//      Main booking logic
        CreateBoardingStudentDto studentToBeCreated = CreateBoardingStudentDto.builder()
                .ticketId(toBebookedBoardingTicketDto.getTicketId())
                .seatId(bookedSeat.getSeatId())
                .firstName(toBebookedBoardingTicketDto.getFirstName())
                .lastName(toBebookedBoardingTicketDto.getLastName())
                .schoolName(toBebookedBoardingTicketDto.getSchoolName())
                .schoolCode(toBebookedBoardingTicketDto.getSchoolCode())
                .educationType(toBebookedBoardingTicketDto.getStuEductionType())
                .schoolDistrict(toBebookedBoardingTicketDto.getSchoolDistrictName())
                .rebCombination(toBebookedBoardingTicketDto.getRebCombination())
                .tvetTrade(toBebookedBoardingTicketDto.getTvetTrade())
                .rebClass(toBebookedBoardingTicketDto.getRebClass())
                .tvetLevel(toBebookedBoardingTicketDto.getTvetLevel())
                .studentLevel(toBebookedBoardingTicketDto.getStudentLevel())
                .departurePoint(toBebookedBoardingTicketDto.getDeparture_point())
                .destinationPoint(toBebookedBoardingTicketDto.getDestination_point())
                .departTime(toBebookedBoardingTicketDto.getDepart_time())
                .arrivalTime(toBebookedBoardingTicketDto.getArrival_time())
                .build();


        BookedBoardingTicketDto bookedTicket = BoardingTicketsMapper.ticketToBookedTickedDto(boardingTicketRepository.save(ticketToBeBooked));

        BoardingStudentDto savedStudent = null;
        if (bookedTicket != null) {
            savedStudent = studentService.registerStudent(studentToBeCreated);
        }
        bookedTicket.setStudentId(savedStudent.getStudentId());
        bookedTicket.setBookedBy(bookingEmail);
        bookedTicket.setFirstName(savedStudent.getFirstName());
        bookedTicket.setLastName(savedStudent.getLastName());
        bookedTicket.setSchoolName(savedStudent.getSchoolName());
        bookedTicket.setSchoolCode(savedStudent.getSchoolCode());
        bookedTicket.setStuEductionType(savedStudent.getEducationType());
        bookedTicket.setSchoolDistrictName(savedStudent.getSchoolDistrict());
        bookedTicket.setRebCombination(savedStudent.getRebCombination());
        bookedTicket.setTvetTrade(savedStudent.getTvetTrade());
        bookedTicket.setRebClass(savedStudent.getRebClass());
        bookedTicket.setTvetLevel(savedStudent.getTvetLevel());
        bookedTicket.setStudentLevel(savedStudent.getStudentLevel());

        return bookedTicket;


    }

    @Override
    public List<BookedBoardingTicketDto> viewBookedTickets() throws ResourceNotFoundException {
        String bookingEmail = userService.getCurrentUser().getParent().getEmail();
        List<SeatDto> bookedSeats = seatService.getSeatsByBooked(bookingEmail);
        Set<UUID> seatsIds = bookedSeats
                .stream()
                .map(SeatDto::getSeatId)
                .collect(Collectors.toSet());

        Set<UUID> ticketIds = bookedSeats
                .stream()
                .map(SeatDto::getTicketId)
                .collect(Collectors.toSet());

        Set<BoardingTicket> bookedSeatsTickets = boardingTicketRepository.findTicketByTicketId(ticketIds);

        Set<BoardingTicketDto> bookedSeatsTicketsDto = bookedSeatsTickets
                .stream()
                .map(BoardingTicketsMapper::ticketToTicketDto)
                .collect(Collectors.toSet());

        List<BoardingStudentDto> studentsWithTicket = null;

        for (UUID ticket_id : ticketIds) {
            for (UUID seat_id : seatsIds) {
                if (studentService.getStuByTicketAndSeatId(ticket_id, seat_id) != null) {
                    studentsWithTicket.add(studentService.getStuByTicketAndSeatId(ticket_id, seat_id));
                }

            }
        }


        List<BookedBoardingTicketDto> bookedTickets = null;
        for (BoardingTicketDto bookedTicket : bookedSeatsTicketsDto) {
            bookedTickets.add(
                    BookedBoardingTicketDto.builder()
                            .ticketId(bookedTicket.getTicketId())
                            .companyId(bookedTicket.getCompanyId())
                            .departure_point(bookedTicket.getDeparture_point())
                            .destination_point(bookedTicket.getDestination_point())
                            .depart_time(bookedTicket.getDepart_time())
                            .arrival_time(bookedTicket.getArrival_time())
                            .duration(bookedTicket.getDuration())
                            .availableSeats(bookedTicket.getAvailableSeats())
                            .price(bookedTicket.getPrice())
                            .assgnedCar(bookedTicket.getAssgnedCar())
                            .assignedDriver(bookedTicket.getAssignedDriver())
                            .status(bookedTicket.getStatus())
                            .build()
            );
        }


        for (BoardingStudentDto studentwithTicket : studentsWithTicket) {
            for (BookedBoardingTicketDto bookedBoardingTicketDto : bookedTickets) {
                if (studentwithTicket.getTicketId() == bookedBoardingTicketDto.getTicketId()) {
                    bookedBoardingTicketDto.setStudentId(studentwithTicket.getStudentId());
                    bookedBoardingTicketDto.setBookedBy(bookingEmail);
                    bookedBoardingTicketDto.setFirstName(studentwithTicket.getFirstName());
                    bookedBoardingTicketDto.setLastName(studentwithTicket.getLastName());
                    bookedBoardingTicketDto.setSchoolName(studentwithTicket.getSchoolName());
                    bookedBoardingTicketDto.setSchoolCode(studentwithTicket.getSchoolCode());
                    bookedBoardingTicketDto.setStuEductionType(studentwithTicket.getEducationType());
                    bookedBoardingTicketDto.setSchoolDistrictName(studentwithTicket.getSchoolDistrict());
                    bookedBoardingTicketDto.setRebCombination(studentwithTicket.getRebCombination());
                    bookedBoardingTicketDto.setTvetTrade(studentwithTicket.getTvetTrade());
                    bookedBoardingTicketDto.setRebClass(studentwithTicket.getRebClass());
                    bookedBoardingTicketDto.setTvetLevel(studentwithTicket.getTvetLevel());
                    bookedBoardingTicketDto.setStudentLevel(studentwithTicket.getStudentLevel());
                }
            }
        }

        return bookedTickets;
//
    }
}
