package rw.app.urugendo.day.models.Ticket.dayTIcket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import rw.app.urugendo.day.models.Ticket.Enum.ETicketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDayTicketDto {

    @NonNull
    private UUID ticketId;

    @NonNull
    private UUID schoolId;

    @NonNull
    private UUID bookedTo;

    @NonNull
    private String bookedBy;

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
