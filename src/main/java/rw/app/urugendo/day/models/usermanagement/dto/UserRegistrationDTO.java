package rw.app.urugendo.day.models.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.app.urugendo.models.usermanagement.Enums.EGender;
import rw.app.urugendo.models.usermanagement.Enums.ERoles;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDTO {
    private String firstName;
    private String LastName;
    private String nationalId;
    private String email;
    private String phoneNumber;
    private EGender gender;
    private String password;
    private ERoles role;
}
