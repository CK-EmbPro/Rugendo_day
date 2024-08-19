package rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto;

import lombok.*;
import rw.app.urugendo.day.models.usermanagement.Enums.EGender;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssignedDriverDto {
    @NonNull
    private String firstName;

    @NonNull
    private UUID ticketId;

    @NonNull
    private String lastName;

    @NonNull
    private int age;

    @NonNull
    private EGender gender;

    @NonNull
    private String email;

    @NonNull
    private UUID schoolId;

    @NonNull
    private String carPlateNo;

    @NonNull
    private String password;
}
