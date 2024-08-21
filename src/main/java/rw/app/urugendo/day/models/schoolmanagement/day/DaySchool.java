package rw.app.urugendo.day.models.schoolmanagement.day;

import jakarta.persistence.*;
import lombok.*;
import rw.app.urugendo.day.models.student.enums.EProvinces;
import rw.app.urugendo.day.models.student.enums.Edistricts;


import java.util.UUID;

@Entity
@Table(name = "day_school")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DaySchool {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "day_school_id")
    private UUID daySchoolId;
    @Column(name = "school_name", nullable = false)
    private String schoolName;
    @Column(name = "school_code",unique = true, nullable = false)
    private String schoolCode;
    @Column(name = "school_district", nullable = false)
    private Edistricts schoolDistrict;
    @Column (name = "school_province", nullable = false)
    private EProvinces schoolProvince;
    @Column(name = "school_email", nullable = false)
    private String schoolEmail;
    @Column(name = "school_phone_number", nullable = false)
    private String schoolPhono;
}
