package rw.app.urugendo.day.models.Ticket.dayTIcket;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import rw.app.urugendo.day.models.Ticket.Enum.ETicketStatus;
import rw.app.urugendo.day.models.utils.TimeStampAudit;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DayTicket extends TimeStampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ticket_id", nullable = false)
    private UUID ticketId;

    @Column(name= "school_id", nullable = false)
    private UUID schoolId;


    @Column(name = "departupre_point", nullable = false)
    private String departurePoint;

    @Column(name = "destination_point", nullable = false)
    private String destinationPoint;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "morning_depart_time", nullable = false)
    private LocalDateTime morningDepartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "morning_arrival_time", nullable = false)
    private LocalDateTime morningArrivalTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "noon_depart_time", nullable = false)
    private LocalDateTime noonDepartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "noon_arrival_time", nullable = false)
    private LocalDateTime noonArrivalTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "evening_depart_time", nullable = false)
    private LocalDateTime eveningDepartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "evening_arrival_time", nullable = false)
    private LocalDateTime eveningArrivalTime;

    @Column(name = "price(RWF)", nullable = false)
    private int price;

    @Column(name = "assigned_car", unique = true, nullable = false)
    private String assignedCar;

    @Column(name = "assigned_driver", nullable = false)
    private String assignedDriver;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ETicketStatus ticketStatus = ETicketStatus.AVAILABLE;

    @Column(name="available_seats", nullable = false)
    private int availableSeats;

}
