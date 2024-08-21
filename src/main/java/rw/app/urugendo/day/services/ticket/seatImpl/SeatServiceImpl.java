package rw.app.urugendo.day.services.ticket.seatImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Ticket.dayTIcket.DayTicket;
import rw.app.urugendo.day.models.Ticket.dayTIcket.Seat;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.CreateSeatDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.DayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.SeatDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.utils.TicketSeatsMapper;
import rw.app.urugendo.day.repositories.tickets.SeatRepostory;
import rw.app.urugendo.day.repositories.tickets.day.DayTicketRepo;
import rw.app.urugendo.day.services.ticket.SeatService;
import rw.app.urugendo.day.services.ticket.day.impl.DayTicketServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeatServiceImpl implements SeatService {
    private final SeatRepostory seatRepostory;
    private final DayTicketRepo dayTicketRepo;

    @Override
    public SeatDto registerTicketSeat(CreateSeatDto createSeatDto) {
        SeatDto seat = null;
        try {
            Optional<DayTicket> correspondingTicket = dayTicketRepo.findById(createSeatDto.getTicketId());
            if (correspondingTicket.isEmpty())throw new ResourceNotFoundException("Assigned ticket " + createSeatDto.getTicketId() + " not found");
            Seat seatToBeSaved = TicketSeatsMapper.createSeatDtoToSeat(createSeatDto);
            seat = TicketSeatsMapper.seatToSeatDto(seatRepostory.save(seatToBeSaved));
        }catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        }catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return seat;

    }

    @Override
    public SeatDto updateTicketSeat(SeatDto seatDto, UUID seatId) {
        SeatDto updatedSeat = null;
        try {
            Optional<Seat> toBeUpdated = seatRepostory.findById(seatId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("Seat not found");

            toBeUpdated.get().setTicketId(seatDto.getTicketId());
            toBeUpdated.get().setSeatIdentifier(seatDto.getSeatIdentifier());
            toBeUpdated.get().setBookedBy(seatDto.getBookedBy());

            updatedSeat =  TicketSeatsMapper.seatToSeatDto(seatRepostory.save(toBeUpdated.get()));

        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        }catch (Exception e) {
            log.error("Something bad happened: {}",e.getMessage());
        }

        return updatedSeat;
    }

    @Override
    public List<SeatDto> getAllSeatsByTicketId(UUID ticketId) {
        List<SeatDto> ticketSeats = new ArrayList<>();
        try {
            List<Seat> allSeatsOnTicket = seatRepostory.findByTicketId(ticketId);
            ticketSeats =  TicketSeatsMapper.seatsListToSeatDtosList(allSeatsOnTicket);
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return ticketSeats;
    }

    @Override
    public List<SeatDto> getAllSeats() {
        List<SeatDto> allSeatsDtos = new ArrayList<>();
        try {
            List<Seat> allSeats = seatRepostory.findAll();
            allSeatsDtos = TicketSeatsMapper.seatsListToSeatDtosList(allSeats);
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return allSeatsDtos;
    }

    @Override
    public SeatDto getSingleSeat(UUID seatId) {
        SeatDto seatDto = null;
        try {
            Optional<Seat> seat = seatRepostory.findById(seatId);
            if (seat.isEmpty()) throw new ResourceNotFoundException("Seat " + seatId + " not found");
            seatDto = TicketSeatsMapper.seatToSeatDto(seat.get());
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }
      return seatDto;
    }

    @Override
    public List<SeatDto> getSeatsByBookedBy(String bookedBy) {
        List<SeatDto> seatsDtos = new ArrayList<>();
        try {
            List<Seat> seatsByBookedBy = seatRepostory.findByBookedBy(bookedBy);
            seatsDtos =  TicketSeatsMapper.seatsListToSeatDtosList(seatsByBookedBy);
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return seatsDtos;
    }

    @Override
    public SeatDto getSeatByTicketId(UUID seatId, UUID ticketId) {
        SeatDto seatDto = null;
        try {

            Optional<Seat> seat = seatRepostory.findBySeatIdAndTicketId(seatId, ticketId);
            if (seat.isEmpty()) throw new ResourceNotFoundException("Specified Seat" + seatId + " of ticket" + ticketId + " not found");
            seatDto = TicketSeatsMapper.seatToSeatDto(seat.get());
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}",e.getMessage());
        }
      return seatDto;
    }

    @Override
    public boolean deleteTicketSeat(UUID seatId) {
        boolean isDeleted = false;
        try {
            Optional<Seat> toBeDeleted = seatRepostory.findById(seatId);
            if (toBeDeleted.isEmpty()) throw new ResourceNotFoundException("Seat " + seatId + " not found");
            seatRepostory.delete(toBeDeleted.get());
            isDeleted = !seatRepostory.existsById(seatId);
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}",e.getMessage());
        }
       return isDeleted;
    }

    @Override
    public boolean deleteAllTicketSeats(UUID ticketId) {
        boolean areDeleted = false;
        try {
            List<Seat> toBeDeleted = seatRepostory.findByTicketId(ticketId);
            seatRepostory.deleteAll(toBeDeleted);
            areDeleted = seatRepostory.existsById(ticketId);
        }catch (Exception e){
            log.error("Something bad happened: {}",e.getMessage());
        }
        return areDeleted;
    }
}
