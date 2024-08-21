package rw.app.urugendo.day.models.Parent.dto;

import lombok.*;
import rw.app.urugendo.day.models.Ticket.Enum.ETicketValidStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentViewTicketsDto {
    @NonNull
    private ETicketValidStatus isTicketValid;
    @NonNull
    private String uniqueIdentifier;

}
