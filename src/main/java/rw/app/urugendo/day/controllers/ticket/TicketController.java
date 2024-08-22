package rw.app.urugendo.day.controllers.ticket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.BookedDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.CreateDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.DayTicketDto;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.ticket.day.impl.DayTicketServiceImpl;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/day/ticket")
@RestController
@RequiredArgsConstructor
public class TicketController {
    private final DayTicketServiceImpl dayTicketService;
    @PostMapping
    public ResponseEntity<ApiResponse<DayTicketDto>> registerTicket(@RequestBody CreateDayTicketDto createDayTicketDto){
        DayTicketDto savedTicket = dayTicketService.registerDayTicket(createDayTicketDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(savedTicket, "Ticket created successfully", "SUCCESS", HttpStatus.CREATED));
    }

    @PutMapping("{ticket_id}")
    public ResponseEntity<ApiResponse<DayTicketDto>> updateTicket(@PathVariable("ticket_id")UUID ticketId, @RequestBody DayTicketDto updatingDto){
        DayTicketDto updatedTicket = dayTicketService.updateDayTicket(ticketId,updatingDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updatedTicket, "Ticket updated successfully", "SUCCESS", HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DayTicketDto>>> getAvailableTickets(){
        List<DayTicketDto> availableDayTickets = dayTicketService.getAvailableDayTickets();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(availableDayTickets, "Available tickets retrieved successfully", "SUCCESS", HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookedDayTicketDto>>> getBookedTickets(){
        List<BookedDayTicketDto> bookedDayTickets = dayTicketService.getBookedDayTickets();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(bookedDayTickets, "Booked tickets retrieved successfully", "SUCCESS", HttpStatus.OK));
    }

    @DeleteMapping("{ticket_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteTicket(@PathVariable("ticket_id") UUID ticketId){
        boolean isDeleted = dayTicketService.deleteDayTicket(ticketId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "Ticket deleted successfully", "SUCCESS", HttpStatus.OK));
    }

    @GetMapping("{ticket_id}")
    public ResponseEntity<ApiResponse<DayTicketDto>> getSingleTicket(@PathVariable("ticket_id") UUID ticketId){
        DayTicketDto dayTicket = dayTicketService.getSingleDayTicket(ticketId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(dayTicket, "Ticket  retrieved successfully", "SUCCESS", HttpStatus.OK));
    }
}
