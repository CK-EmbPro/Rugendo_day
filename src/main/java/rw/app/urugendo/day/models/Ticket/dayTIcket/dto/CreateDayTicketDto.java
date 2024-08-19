package rw.app.urugendo.day.models.Ticket.dayTIcket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import rw.app.urugendo.day.models.Ticket.Enum.ETicketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDayTicketDto {

    @NonNull
    private UUID schoolId;

    @NonNull
    private String bookedTo = "none";

    @NonNull
    private String bookedBy = "none";

    @NonNull
    private String departurePoint;

    @NonNull
    private String destinationPoint;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NonNull
    private LocalDateTime morningDepartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NonNull
    private LocalDateTime morningArrivalTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NonNull
    private LocalDateTime noonDepartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NonNull
    private LocalDateTime noonArrivalTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NonNull
    private LocalDateTime eveningDepartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NonNull
    private LocalDateTime eveningArrivalTime;

    @NonNull
    private int price;

    @NonNull
    private String assignedCar;

    @NonNull
    private String assignedDriver;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ETicketStatus ticketStatus = ETicketStatus.AVAILABLE;

    @NonNull
    private int availableSeats;
}
