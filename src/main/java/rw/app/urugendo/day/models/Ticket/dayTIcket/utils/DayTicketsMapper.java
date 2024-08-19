package rw.app.urugendo.day.models.Ticket.dayTIcket.utils;


import rw.app.urugendo.day.models.Ticket.dayTIcket.DayTicket;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.CreateDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.DayTicketDto;

public class DayTicketsMapper {

    public static DayTicket createDayTicketDtoToDayTicket(CreateDayTicketDto createDayTicketDto) {
        return DayTicket.builder()
                .schoolId(createDayTicketDto.getSchoolId())
                .departurePoint(createDayTicketDto.getDeparturePoint())
                .destinationPoint(createDayTicketDto.getDestinationPoint())
                .morningDepartTime(createDayTicketDto.getMorningDepartTime())
                .morningArrivalTime(createDayTicketDto.getMorningArrivalTime())
                .noonArrivalTime(createDayTicketDto.getNoonArrivalTime())
                .noonDepartTime(createDayTicketDto.getNoonDepartTime())
                .eveningArrivalTime(createDayTicketDto.getEveningArrivalTime())
                .eveningDepartTime(createDayTicketDto.getEveningDepartTime())
                .price(createDayTicketDto.getPrice())
                .assignedDriver(createDayTicketDto.getAssignedDriver())
                .assignedCar(createDayTicketDto.getAssignedCar())
                .ticketStatus(createDayTicketDto.getTicketStatus())
                .build();
    }

//    public static BookedDayTicketDto ticketToBookedTickedDto(DayTicket ticket) {
//        return BookedDayTicketDto.builder()
//
//                .build();
//
//    }
//
//    public static DayTicket BookedTicketDtoToTicket(BookedDayTicketDto bookedDayTicketDto) {
//        return DayTicket.builder()
//
//                .build();
//
//
//    }


    public static DayTicket dayTicketDtoToDayTicket(DayTicketDto dayTicketDto) {
        return DayTicket.builder()
                .ticketId(dayTicketDto.getTicketId())
                .schoolId(dayTicketDto.getSchoolId())
                .departurePoint(dayTicketDto.getDeparturePoint())
                .destinationPoint(dayTicketDto.getDestinationPoint())
                .morningDepartTime(dayTicketDto.getMorningDepartTime())
                .morningArrivalTime(dayTicketDto.getMorningArrivalTime())
                .noonArrivalTime(dayTicketDto.getNoonArrivalTime())
                .noonDepartTime(dayTicketDto.getNoonDepartTime())
                .eveningArrivalTime(dayTicketDto.getEveningArrivalTime())
                .eveningDepartTime(dayTicketDto.getEveningDepartTime())
                .price(dayTicketDto.getPrice())
                .assignedDriver(dayTicketDto.getAssignedDriver())
                .assignedCar(dayTicketDto.getAssignedCar())
                .ticketStatus(dayTicketDto.getTicketStatus())
                .build();
    }

    public static DayTicketDto dayTicketToDayTicketDto(DayTicket dayTicket) {
        return DayTicketDto.builder()
                .ticketId(dayTicket.getTicketId())
                .schoolId(dayTicket.getSchoolId())
                .departurePoint(dayTicket.getDeparturePoint())
                .destinationPoint(dayTicket.getDestinationPoint())
                .morningDepartTime(dayTicket.getMorningDepartTime())
                .morningArrivalTime(dayTicket.getMorningArrivalTime())
                .noonArrivalTime(dayTicket.getNoonArrivalTime())
                .noonDepartTime(dayTicket.getNoonDepartTime())
                .eveningArrivalTime(dayTicket.getEveningArrivalTime())
                .eveningDepartTime(dayTicket.getEveningDepartTime())
                .price(dayTicket.getPrice())
                .assignedDriver(dayTicket.getAssignedDriver())
                .assignedCar(dayTicket.getAssignedCar())
                .ticketStatus(dayTicket.getTicketStatus())
                .build();
    }

}
