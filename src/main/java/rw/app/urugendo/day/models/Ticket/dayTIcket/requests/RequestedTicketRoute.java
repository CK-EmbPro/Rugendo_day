package rw.app.urugendo.day.models.Ticket.dayTIcket.requests;

import jakarta.persistence.*;
import lombok.*;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.enums.ERouteRequestStatus;

import java.util.UUID;

@Entity
@Table(name = "requested_day_ticket_routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestedTicketRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "requested_route_id")
    private UUID requestedRouteId;

    @Column(name = "departure_point")
    private String departurePoint;

    @Column(name = "destination_point")
    private String destinationPoint;

    @Column(name = "requested_by")
    private String requestedBy;

    @Column(name = "n_of_seats_requested")
    private int nOfSeatsRequested;

    @Column(name = "school_id")
    private UUID schoolId;

    @Column(name = "route_status")
    @Enumerated(EnumType.STRING)
    private ERouteRequestStatus routeRequestStatus;


}
