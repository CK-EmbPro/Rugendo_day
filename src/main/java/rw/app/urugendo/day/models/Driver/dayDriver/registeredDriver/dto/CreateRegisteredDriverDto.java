package rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto;

import lombok.*;
import rw.app.urugendo.day.models.usermanagement.Enums.EGender;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRegisteredDriverDto {
    @NonNull
    private String firstName;


    @NonNull
    private String lastName;

    @NonNull
    private int age;

    @NonNull
    private EGender gender;

    @NonNull
    private String email;

    @NonNull
    private UUID schoolCode;

    @NonNull
    private String password;
}
