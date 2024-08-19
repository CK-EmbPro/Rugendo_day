package rw.app.urugendo.day.models.Ticket.dayTIcket.utils;


import rw.app.urugendo.day.models.Ticket.dayTIcket.Seat;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.CreateSeatDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.SeatDto;

import java.util.List;

public class TicketSeatsMapper {
    public static List<SeatDto> seatsListToSeatDtosList(List<Seat> seatList){
        return seatList.stream().map(TicketSeatsMapper::seatToSeatDto).toList();
    }
    public static SeatDto seatToSeatDto(Seat ticketSeat){
        return SeatDto.builder()
                .seatId(ticketSeat.getSeatId())
                .seatIdentifier(ticketSeat.getSeatIdentifier())
                .ticketId(ticketSeat.getTicketId())
                .bookedBy(ticketSeat.getBookedBy())
                .build();
    }

    public static List<Seat> seatsDtoListToSeatsList(List<SeatDto> seatDtoList){
        return seatDtoList.stream().map(TicketSeatsMapper::seatDtoToSeat ).toList();
    }
    public static Seat seatDtoToSeat(SeatDto seatDto){
        return Seat.builder()
                .seatId(seatDto.getSeatId())
                .seatIdentifier(seatDto.getSeatIdentifier())
                .ticketId(seatDto.getTicketId())
                .bookedBy(seatDto.getBookedBy())
                .build();
    }

    public static Seat createSeatDtoToSeat(CreateSeatDto createSeatDto){
        return Seat.builder()
                .seatIdentifier(createSeatDto.getSeatIdentifier())
                .ticketId(createSeatDto.getTicketId())
                .bookedBy(createSeatDto.getBookedBy())
                .build();
    }
}
