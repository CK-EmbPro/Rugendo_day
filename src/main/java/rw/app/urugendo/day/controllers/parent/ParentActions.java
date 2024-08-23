package rw.app.urugendo.day.controllers.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.BookedDayTicketDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.dto.BookingDayTicketDto;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.parent.impl.ParentActionsImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/day/parent")
@RequiredArgsConstructor
public class ParentActions {
    private final ParentActionsImpl parentActions;

    @PostMapping
    public ResponseEntity<ApiResponse<BookedDayTicketDto>> bookStudentTicket(@RequestBody BookingDayTicketDto bookingDayTicketDto){
        BookedDayTicketDto bookedTicket = parentActions.bookStudentTicket(bookingDayTicketDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(bookedTicket, "Booked ticket successfully", "SUCCESS", HttpStatus.CREATED));

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookedDayTicketDto>>> viewBookedTickets(){
        List<BookedDayTicketDto> bookedTickets = parentActions.viewBookedTickets();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(bookedTickets, "All booked ticket retrieved successfully", "SUCCESS", HttpStatus.OK
                ));

    }
}
