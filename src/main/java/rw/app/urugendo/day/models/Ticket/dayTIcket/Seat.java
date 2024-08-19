package rw.app.urugendo.day.models.Ticket.dayTIcket;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="ticket_seat")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "seat_id")
    private UUID seatId;

    @Column(name = "ticket_id")
    private UUID ticketId;

    @Column(name = "seat_identifier")
    private String seatIdentifier;

    @Column(name = "bookedBy")
    private String bookedBy;

}
