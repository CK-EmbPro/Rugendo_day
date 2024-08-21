package rw.app.urugendo.day.models.student.dayStudent.dto;

import lombok.*;
import rw.app.urugendo.day.models.student.enums.EProvinces;
import rw.app.urugendo.day.models.student.enums.Edistricts;
import rw.app.urugendo.day.models.usermanagement.Enums.EGender;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDayStudentDto {

    @NonNull
    private UUID schoolId;

    @NonNull
    private String firstName;

    @NonNull
    private EGender stuGender;


    @NonNull
    private String lastName;

    @NonNull
    private String father_firstName;

    @NonNull
    private String father_lastName;

    @NonNull
    private String mother_firstName;

    @NonNull
    private String mother_lastName;

    @NonNull
    private String father_phono;

    @NonNull
    private String mother_phono;

    @NonNull
    private String schoolName;

    @NonNull
    private EProvinces stuProvince;

    @NonNull
    private Edistricts stuDistrict;

    @NonNull
    private String stuSector;

    @NonNull
    private String stuCell;

    @NonNull
    private String stuVillage;

    @NonNull
    private String streetInfo;

}
