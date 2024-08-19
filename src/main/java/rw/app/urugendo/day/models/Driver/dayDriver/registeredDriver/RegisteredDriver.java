package rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver;

import jakarta.persistence.*;
import lombok.*;
import rw.app.urugendo.day.models.usermanagement.Enums.EGender;

import java.util.UUID;

@Entity
@Table(name="registered_day_drivers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredDriver {
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

    @Column(name = "password", nullable = false)
    private String password;

}