package rw.app.urugendo.day.models.schoolmanagement.day.dto;

import lombok.*;
import rw.app.urugendo.models.student.enums.EProvinces;
import rw.app.urugendo.models.student.enums.Edistricts;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDaySchoolDto {
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
