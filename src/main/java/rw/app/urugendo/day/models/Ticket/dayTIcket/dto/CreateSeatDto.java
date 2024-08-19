package rw.app.urugendo.day.models.Ticket.dayTIcket.dto;

import lombok.*;

import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSeatDto {
    private UUID ticketId;
    private String seatIdentifier;
    private String bookedBy;
}
