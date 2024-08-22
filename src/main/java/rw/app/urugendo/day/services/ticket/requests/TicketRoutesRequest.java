package rw.app.urugendo.day.services.ticket.requests;

import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.RequestedTicketRoute;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.CreateTicketRouteRequest;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestTicketRouteDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestUpdatingDto;

import java.util.List;
import java.util.UUID;

public interface TicketRoutesRequest {
    RequestTicketRouteDto requestRoute(CreateTicketRouteRequest createTicketRouteRequest);

    RequestTicketRouteDto updateRequest(UUID requestId, RequestUpdatingDto requestUpdatingDto);

    RequestTicketRouteDto getSingleRequest(UUID requestId);

    List<RequestTicketRouteDto> getAllRequests();

    List<RequestTicketRouteDto> getAllByRequestedBy();

    List<RequestTicketRouteDto> getAllBySchoolId(UUID schoolId);

    boolean deleteRequest(UUID requestId);
}

