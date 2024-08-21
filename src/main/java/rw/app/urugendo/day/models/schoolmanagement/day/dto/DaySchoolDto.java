package rw.app.urugendo.day.models.schoolmanagement.day.dto;

import lombok.*;
import rw.app.urugendo.day.models.student.enums.EProvinces;
import rw.app.urugendo.day.models.student.enums.Edistricts;


import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DaySchoolDto {
    @NonNull
    private UUID daySchoolId;
    @NonNull
    private String schoolName;
    @NonNull
    private String schoolCode;
    @NonNull
    private Edistricts schoolDistrict;
    @NonNull
    private EProvinces schoolProvince;
    @NonNull
    private String schoolEmail;
    @NonNull
    private String schoolPhono;
}
