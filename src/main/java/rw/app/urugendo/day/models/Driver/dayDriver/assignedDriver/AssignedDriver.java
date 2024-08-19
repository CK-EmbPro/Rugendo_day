package rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver;

import jakarta.persistence.*;
import lombok.*;
import rw.app.urugendo.day.models.usermanagement.Enums.EGender;

import java.util.UUID;

@Entity
@Table(name="assigned_day_drivers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignedDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "driver_id", nullable = false)
    private UUID dayDriverId;

    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name= "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false)
    private EGender gender;

    @Column(name="unique_identifier", unique = true, nullable = false)
    private String email;

    @Column(name= "school_code", nullable = false)
    private UUID schoolId;

    @Column(name = "ticket_id", nullable = false, unique = true)
    private UUID ticketId;

    @Column(name= "car_plate_no", nullable = false)
    private String carPlateNo;

    @Column(name = "password", nullable = false)
    private String password;
}
