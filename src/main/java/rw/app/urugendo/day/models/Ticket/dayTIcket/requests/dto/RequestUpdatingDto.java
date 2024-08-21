package rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto;

import lombok.*;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.enums.ERouteRequestStatus;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class RequestUpdatingDto {
    @NonNull
    private UUID requestedRouteId;
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
