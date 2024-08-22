package rw.app.urugendo.day.services.ticket.day.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Bus.dayBus.dto.DayBusDto;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.AssignedDriver;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto.AssignedDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.utils.AssignedDriverMapper;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.RegisteredDriverDto;
import rw.app.urugendo.day.models.Notifications.dto.CreateNotificationDto;
import rw.app.urugendo.day.models.Notifications.dto.NotificationDto;
import rw.app.urugendo.day.models.Ticket.Enum.ETicketStatus;
import rw.app.urugendo.day.models.Ticket.dayTIcket.DayTicket;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.BookedDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.CreateDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.DayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.SeatDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.utils.DayTicketsMapper;
import rw.app.urugendo.day.repositories.Driver.day.assigned.AssignedDayDriverRepo;
import rw.app.urugendo.day.repositories.tickets.day.DayTicketRepo;
import rw.app.urugendo.day.services.Bus.day.impl.DayBusServiceImpl;
import rw.app.urugendo.day.services.Driver.day.impl.DayDriverServiceImpl;
import rw.app.urugendo.day.services.notifications.impl.NotifyServiceImpl;
import rw.app.urugendo.day.services.ticket.day.DayTicketService;
import rw.app.urugendo.day.services.ticket.seatImpl.SeatServiceImpl;
import rw.app.urugendo.day.services.usermanagement.impl.UserServiceImpl;

import java.util.ArrayList;
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
    private final AssignedDayDriverRepo assignedDayDriverRepo;
    private final SeatServiceImpl seatService;
    private final UserServiceImpl userService;
    private final NotifyServiceImpl notifyService;

    private String currentUser  = userService.getCurrentUser().getParent().getEmail();

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

                CreateNotificationDto notifyDto = CreateNotificationDto.builder()
                        .sentTo(currentUser)
                        .message("Congratulations !! you've managed to create school_bus ticket")
                        .triggeringAction("REGISTRATION OF SCHOOL_BUS TICKET")
                        .build();
                notifyService.registerNotify(notifyDto);
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
        UUID ticketId1 = dayTicketDto.getTicketId();
        UUID schoolId = dayTicketDto.getSchoolId();
        try {
            Optional<DayTicket> toBeUpdated = dayTicketRepo.findById(ticketId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("Ticket not found");

            RegisteredDriverDto registeredDriver = dayDriverService.getRegisteredDriverByEmail(email);
            DayBusDto registeredBus = busService.getDayBusByPlateNo(plateNo);
            AssignedDriverDto assignedDriver = dayDriverService.getAssignedDriverByTicketId(ticketId1);


            if (!assignedDriver.getEmail().equals(dayTicketDto.getAssignedDriver())){
                assignedDriver.setEmail(dayTicketDto.getAssignedDriver());
            }
            if (!assignedDriver.getCarPlateNo().equals(dayTicketDto.getAssignedCar())){
                assignedDriver.setCarPlateNo(dayTicketDto.getAssignedCar());
            }
            if(!assignedDriver.getSchoolId().equals(dayTicketDto.getSchoolId())){
                assignedDriver.setSchoolId(dayTicketDto.getSchoolId());
            }
            assignedDayDriverRepo.save(AssignedDriverMapper.assignedDriverDtoToAssignedDriver(assignedDriver));

            toBeUpdated.get().setSchoolId(dayTicketDto.getSchoolId());
            toBeUpdated.get().setDeparturePoint(dayTicketDto.getDeparturePoint());
            toBeUpdated.get().setDestinationPoint(dayTicketDto.getDestinationPoint());
            toBeUpdated.get().setMorningArrivalTime(dayTicketDto.getMorningArrivalTime());
            toBeUpdated.get().setMorningDepartTime(dayTicketDto.getMorningDepartTime());
            toBeUpdated.get().setNoonArrivalTime(dayTicketDto.getNoonArrivalTime());
            toBeUpdated.get().setNoonDepartTime(dayTicketDto.getNoonDepartTime());
            toBeUpdated.get().setEveningArrivalTime(dayTicketDto.getEveningArrivalTime());
            toBeUpdated.get().setEveningDepartTime(dayTicketDto.getEveningDepartTime());
            toBeUpdated.get().setPrice(dayTicketDto.getPrice());
            toBeUpdated.get().setAssignedCar(dayTicketDto.getAssignedCar());
            toBeUpdated.get().setAssignedDriver(dayTicketDto.getAssignedDriver());
            toBeUpdated.get().setTicketStatus(dayTicketDto.getTicketStatus());

            DayTicket updatedTicket = dayTicketRepo.save(toBeUpdated.get());
            ticketDto = DayTicketsMapper.dayTicketToDayTicketDto(updatedTicket);

            CreateNotificationDto notifyDto = CreateNotificationDto.builder()
                    .sentTo(currentUser)
                    .message("Congratulations !! you've managed to update school_bus ticket: "+updatedTicket.getTicketId())
                    .triggeringAction("UPDATE OF SCHOOL_BUS TICKET")
                    .build();

            notifyService.registerNotify(notifyDto);
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }
    return ticketDto;
    }



    @Override
    public List<DayTicketDto> getAvailableDayTickets() {
        List<DayTicketDto> availableTickets = new ArrayList<>();
        try {
            availableTickets = dayTicketRepo.findDayTicketsByTicketStatus(ETicketStatus.AVAILABLE)
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(
                            DayTicketsMapper::dayTicketToDayTicketDto
                    )
                    .toList();

        }catch (Exception e ){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return availableTickets;

    }

    @Override
    public List<BookedDayTicketDto> getBookedDayTickets() {
        List<BookedDayTicketDto> bookedTicketSeats = new ArrayList<>();
        try {
            List<DayTicketDto> bookedTickets = dayTicketRepo.findDayTicketsByTicketStatus(ETicketStatus.BOOKED)
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(
                            DayTicketsMapper::dayTicketToDayTicketDto
                    )
                    .collect(Collectors.toList());

            for(DayTicketDto dayTicket: bookedTickets){
                UUID ticketId = dayTicket.getTicketId();

                List<SeatDto> associatedSeats = seatService.getAllSeatsByTicketId(ticketId);
                for (SeatDto seat: associatedSeats){
                    BookedDayTicketDto bookedDayTicket = BookedDayTicketDto.builder()
                            .ticketId(ticketId)
                            .seatId(seat.getSeatId())
                            .schoolId(dayTicket.getSchoolId())
                            .bookedBy(seat.getBookedBy())
                            .departurePoint(dayTicket.getDeparturePoint())
                            .destinationPoint(dayTicket.getDestinationPoint())
                            .morningArrivalTime(dayTicket.getMorningArrivalTime())
                            .morningDepartTime(dayTicket.getMorningDepartTime())
                            .noonArrivalTime(dayTicket.getNoonArrivalTime())
                            .noonDepartTime(dayTicket.getEveningDepartTime())
                            .eveningArrivalTime(dayTicket.getEveningArrivalTime())
                            .eveningDepartTime(dayTicket.getNoonDepartTime())
                            .price(dayTicket.getPrice())
                            .assignedCar(dayTicket.getAssignedCar())
                            .assignedDriver(dayTicket.getAssignedDriver())
                            .ticketStatus(dayTicket.getTicketStatus())
                            .availableSeats(dayTicket.getAvailableSeats())
                            .build();

                    bookedTicketSeats.add(bookedDayTicket);
                }

            }

        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }
        return bookedTicketSeats;

    }

    @Override
    public boolean deleteDayTicket(UUID ticketId) {
        boolean isDeleted = false;
        try {
            Optional<AssignedDriver> driverToBeDeleted = assignedDayDriverRepo.findAssignedDriverByTicketId(ticketId);
            if (driverToBeDeleted.isEmpty()) throw new ResourceNotFoundException("The assigned driver on ticket not found");

            Optional<DayTicket> ticket = dayTicketRepo.findById(ticketId);
            if(ticket.isEmpty()) throw new ResourceNotFoundException("ticket not found");
//+++++++++++++++Notifying user++++++++++++++++++++
            CreateNotificationDto notifyDto = CreateNotificationDto.builder()
                    .sentTo(currentUser)
                    .message("Congratulations !! you've managed to delete school_bus ticket: "+ticket.get().getTicketId())
                    .triggeringAction("DELETIONS OF SCHOOL_BUS TICKET")
                    .build();

//+++++++++++++++Notifying user++++++++++++++++++++

            dayTicketRepo.delete(ticket.get());

//+++++++++++++++Notifying user++++++++++++++++++++
            notifyService.registerNotify(notifyDto);
//+++++++++++++++Notifying user++++++++++++++++++++

        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        } catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public DayTicketDto getSingleDayTicket(UUID ticketId) {
        DayTicketDto ticketDto = null;
        try {
            Optional<DayTicket> ticket = dayTicketRepo.findById(ticketId);
            if (ticket.isEmpty()) throw new ResourceNotFoundException("ticket not found");
            ticketDto = DayTicketsMapper.dayTicketToDayTicketDto(ticket.get());
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        
        return ticketDto;
    }


}
