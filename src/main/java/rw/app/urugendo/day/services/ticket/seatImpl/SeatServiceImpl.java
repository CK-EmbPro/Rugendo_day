package rw.app.urugendo.day.services.ticket.seatImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.models.Ticket.dayTIcket.Seat;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.CreateSeatDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.DayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.SeatDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.utils.TicketSeatsMapper;
import rw.app.urugendo.day.repositories.tickets.SeatRepostory;
import rw.app.urugendo.day.services.ticket.SeatService;
import rw.app.urugendo.day.services.ticket.day.impl.DayTicketServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepostory seatRepostory;
    private final DayTicketServiceImpl ticketService;
    @Override
    public SeatDto registerTicketSeat(CreateSeatDto createSeatDto) {
        DayTicketDto correspondingTicket = ticketService.getSingleDayTicket(createSeatDto.getTicketId());
        if(correspondingTicket ==null) throw new EntityNotFoundException("Assigned ticket "+createSeatDto.getTicketId()+" not found");
        Seat seatToBeSaved = TicketSeatsMapper.createSeatDtoToSeat(createSeatDto);
        return TicketSeatsMapper.seatToSeatDto(seatRepostory.save(seatToBeSaved));
    }

    @Override
    public SeatDto updateTicketSeat(SeatDto seatDto, UUID seatId) {
        Optional<Seat> toBeUpdated = seatRepostory.findById(seatId);
        if (toBeUpdated.isPresent()){
            toBeUpdated.get().setTicketId(seatDto.getTicketId());
            toBeUpdated.get().setSeatIdentifier(seatDto.getSeatIdentifier());
            toBeUpdated.get().setBookedBy(seatDto.getBookedBy());

            return TicketSeatsMapper.seatToSeatDto(seatRepostory.save(toBeUpdated.get()));
        }
        throw new EntityNotFoundException("Ticket seat "+seatId+" not found");
    }

    @Override
    public List<SeatDto> getAllSeatsByTicketId(UUID ticketId) {
        List<Seat> allSeatsOnTicket = seatRepostory.findByTicketId(ticketId);
        return TicketSeatsMapper.seatsListToSeatDtosList(allSeatsOnTicket);
    }

    @Override
    public List<SeatDto> getAllSeats() {
        List<Seat> allSeats = seatRepostory.findAll();
        return TicketSeatsMapper.seatsListToSeatDtosList(allSeats);
    }

    @Override
    public SeatDto getSingleSeat(UUID seatId) {
        Optional<Seat> seat = seatRepostory.findById(seatId);
        if(seat.isEmpty()){
            throw new EntityNotFoundException("Seat "+seatId+" not found");
        }
         return TicketSeatsMapper.seatToSeatDto(seat.get());
    }

    @Override
    public List<SeatDto> getSeatsByBooked(String bookedBy) {
        List<Seat> seatsByBookedBy = seatRepostory.findByBookedBy(bookedBy);
        return TicketSeatsMapper.seatsListToSeatDtosList(seatsByBookedBy);
    }

    @Override
    public SeatDto getSeatByTicketId(UUID seatId, UUID ticketId) {
        Optional<Seat> seat = seatRepostory.findBySeatIdAndTicketId(seatId, ticketId);
        if (seat.isEmpty()){
            throw new EntityNotFoundException("Specified Seat"+seatId+" of ticket"+ticketId+" not found");
        }
        return TicketSeatsMapper.seatToSeatDto(seat.get());
    }

    @Override
    public boolean deleteTicketSeat(UUID seatId) {
        Optional<Seat> toBeDeleted = seatRepostory.findById(seatId);
        if(toBeDeleted.isEmpty()) throw new EntityNotFoundException("Seat "+seatId+" not found");
        seatRepostory.delete(toBeDeleted.get());
        return !seatRepostory.existsById(seatId);
    }

    @Override
    public boolean deleteAllTicketSeats(UUID ticketId) {
        List<Seat> toBeDeleted = seatRepostory.findByTicketId(ticketId);
        seatRepostory.deleteAll(toBeDeleted);
        return seatRepostory.existsById(ticketId);
    }
}
