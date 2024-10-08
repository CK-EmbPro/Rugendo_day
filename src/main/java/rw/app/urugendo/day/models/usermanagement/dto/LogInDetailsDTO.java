package rw.app.urugendo.day.models.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.app.urugendo.day.models.usermanagement.Enums.ERoles;
import rw.app.urugendo.day.models.usermanagement.User;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogInDetailsDTO {
    private String token;
    private ERoles role;
    private User parent;

}
