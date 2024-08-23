package rw.app.urugendo.day.controllers.requests;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.CreateTicketRouteRequest;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestTicketRouteDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestUpdatingDto;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.ticket.requests.impl.TicketRoutesRequestImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/day/request")
@RequiredArgsConstructor
public class RequestController {
    private final TicketRoutesRequestImpl requestTicketService;
    @PostMapping
    public ResponseEntity<ApiResponse<RequestTicketRouteDto>> requestRoute(@RequestBody CreateTicketRouteRequest createTicketRouteRequest){
        RequestTicketRouteDto requestedTicket = requestTicketService.requestRoute(createTicketRouteRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(requestedTicket, "Request ticket route successfully", "SUCCESS", HttpStatus.CREATED));
    }

    @PutMapping("{request_id}")
    public ResponseEntity<ApiResponse<RequestTicketRouteDto>> updateRequest(@PathVariable("request_id")UUID requestId, @RequestBody RequestUpdatingDto updatingDto){
        RequestTicketRouteDto updatedRequest = requestTicketService.updateRequest(requestId, updatingDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updatedRequest, "Updated ticket_route request successfully", "SUCCESS", HttpStatus.CREATED));
    }

    @GetMapping("{request_id}")
    public ResponseEntity<ApiResponse<RequestTicketRouteDto>> getSingleRequest(@PathVariable("request_id")UUID requestId){
        RequestTicketRouteDto ticketRequest = requestTicketService.getSingleRequest(requestId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ticketRequest, "Retrived ticket_route request "+requestId+" successfully", "SUCCESS", HttpStatus.CREATED));
    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse<List<RequestTicketRouteDto>>> getAllRequests(){
        List<RequestTicketRouteDto> ticketRequests = requestTicketService.getAllRequests();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ticketRequests, "Retrived all ticket_route requests successfully", "SUCCESS", HttpStatus.CREATED));
    }


    @GetMapping()
    public ResponseEntity<ApiResponse<List<RequestTicketRouteDto>>> getAllByRequestedBy(){
        List<RequestTicketRouteDto> ticketRequests = requestTicketService.getAllByRequestedBy();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ticketRequests, "Retrieved all ticket_route u requested successfully ", "SUCCESS", HttpStatus.CREATED));
    }

    @GetMapping("{school_id}")
    public ResponseEntity<ApiResponse<List<RequestTicketRouteDto>>> getAllBySchoolId(@PathVariable("school_id") UUID schoolId){
        List<RequestTicketRouteDto> ticketRequests = requestTicketService.getAllBySchoolId(schoolId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ticketRequests, "Retrieved all ticket_route u requested to school "+schoolId+" successfully ", "SUCCESS", HttpStatus.CREATED));
    }

    @GetMapping("{request_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteRequest(@PathVariable("request_id") UUID requestId){
        boolean isDeleted = requestTicketService.deleteRequest(requestId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "Deleted ticket_route request "+requestId+" successfully ", "SUCCESS", HttpStatus.CREATED));
    }
}
