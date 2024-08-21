package rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto;

import lombok.*;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.enums.ERouteRequestStatus;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateTicketRouteRequest {
    @NonNull
    private String departurePoint;

    @NonNull
    private String destinationPoint;

    @NonNull
    private String requestedBy;

    @NonNull
    private int nOfSeatsRequested;

    @NonNull
    private UUID schoolId;



}
