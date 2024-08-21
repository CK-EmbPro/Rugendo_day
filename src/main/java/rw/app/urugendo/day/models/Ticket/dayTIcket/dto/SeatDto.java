package rw.app.urugendo.day.models.Ticket.dayTIcket.dto;

import lombok.*;

import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDto {
    private UUID seatId;
    private UUID ticketId;
    private String seatIdentifier;
    private String bookedBy;
    private UUID bookedTo;
}
