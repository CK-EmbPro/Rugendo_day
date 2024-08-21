package rw.app.urugendo.day.models.Ticket.dayTIcket.requests.utils;

import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.RequestedTicketRoute;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.CreateTicketRouteRequest;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestTicketRouteDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.enums.ERouteRequestStatus;

public class RequestedTicketRouteMapper {
    public static RequestedTicketRoute createTicketRouteToRequestedRoute(CreateTicketRouteRequest createTicketRouteRequest){
        return RequestedTicketRoute.builder()
                .departurePoint(createTicketRouteRequest.getDeparturePoint())
                .destinationPoint(createTicketRouteRequest.getDestinationPoint())
                .requestedBy(createTicketRouteRequest.getRequestedBy())
                .nOfSeatsRequested(createTicketRouteRequest.getNOfSeatsRequested())
                .schoolId(createTicketRouteRequest.getSchoolId())
                .routeRequestStatus(ERouteRequestStatus.PENDING)
                .build();
    }

    public static RequestedTicketRoute routeDtoToroute(RequestTicketRouteDto requestTicketRouteDto){
        return RequestedTicketRoute.builder()

                .requestedRouteId(requestTicketRouteDto.getRequestedRouteId())
                .departurePoint(requestTicketRouteDto.getDeparturePoint())
                .destinationPoint(requestTicketRouteDto.getDestinationPoint())
                .requestedBy(requestTicketRouteDto.getRequestedBy())
                .nOfSeatsRequested(requestTicketRouteDto.getNOfSeatsRequested())
                .schoolId(requestTicketRouteDto.getSchoolId())
                .routeRequestStatus(requestTicketRouteDto.getRouteRequestStatus())
                .build();
    }

    public static RequestTicketRouteDto routeTorouteDto(RequestedTicketRoute requestTicketRouteDto){
        return RequestTicketRouteDto.builder()
                .requestedRouteId(requestTicketRouteDto.getRequestedRouteId())
                .departurePoint(requestTicketRouteDto.getDeparturePoint())
                .destinationPoint(requestTicketRouteDto.getDestinationPoint())
                .requestedBy(requestTicketRouteDto.getRequestedBy())
                .nOfSeatsRequested(requestTicketRouteDto.getNOfSeatsRequested())
                .schoolId(requestTicketRouteDto.getSchoolId())
                .routeRequestStatus(requestTicketRouteDto.getRouteRequestStatus())
                .build();
    }



}
