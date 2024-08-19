package rw.app.urugendo.day.models.Bus.dayBus;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DayBus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "day_bus_id", nullable = false )
    private UUID dayBusId;


    @Column(name = "n_of_seats", nullable = false)
    private int n_of_seats;

    @Column(name = "gps_id", unique = true, nullable = false)
    private UUID gps_id;

    @Column(name = "plate_no", nullable = false)
    private String plateNo;

    @Column(name = "school_id", nullable = false)
    private UUID schoolId;


}
