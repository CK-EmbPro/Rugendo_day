package rw.app.urugendo.day.models.usermanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.app.urugendo.day.models.usermanagement.Enums.EGender;
import rw.app.urugendo.day.models.usermanagement.Enums.ERoles;
import rw.app.urugendo.day.models.utils.TimeStampAudit;


import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends TimeStampAudit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "national_id",unique = true)
    private String uniqueIdentifier;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERoles roles;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private EGender gender;
    public User( String phoneNumber,String firstName,String lastName,String password,String uniqueIdentifier,String email,ERoles roles,EGender gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.uniqueIdentifier = uniqueIdentifier;
        this.phoneNumber= phoneNumber;
        this.email = email;
        this.roles = roles;
        this.gender = gender;
    }

//    Saving company as a user
    public User(String companyPhono, String compfirstName,String complastName, String companyEmail, String password){
        this.email=companyEmail;
        this.firstName = compfirstName;
        this.lastName = complastName;
        this.phoneNumber = companyPhono;
        this.password = password;
        this.uniqueIdentifier = email;
        this.gender= EGender.NONE;
        this.roles =ERoles.COMPANY;
    }

}
