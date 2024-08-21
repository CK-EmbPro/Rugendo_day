package rw.app.urugendo.day.models.student.dayStudent;


import jakarta.persistence.*;
import lombok.*;
import rw.app.urugendo.day.models.student.enums.EProvinces;
import rw.app.urugendo.day.models.student.enums.Edistricts;
import rw.app.urugendo.day.models.usermanagement.Enums.EGender;
import rw.app.urugendo.day.models.utils.TimeStampAudit;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "day_students")
public class DayStudent extends TimeStampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "school_id")
    private UUID schoolId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "father's_firstname", nullable = false)
    private String father_firstName;

    @Column(name = "father's_lastname", nullable = false)
    private String father_lastName;

    @Column(name = "mother's_firstname", nullable = false)
    private String mother_firstName;

    @Column(name = "mother's_lastname", nullable = false)
    private String mother_lastName;

    @Column(name = "father's_phoneNumber", nullable = false)
    private String father_phono;

    @Column(name = "mother's_phoneNumber", nullable = false)
    private String mother_phono;

    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @Column(name = "stu_gender", nullable = false)
    private EGender stuGender;

    @Column(name = "stu_province", nullable = false)
    private EProvinces stuProvince;

    @Column(name = "stu_district", nullable = false)
    private Edistricts stuDistrict;

    @Column(name = "stu_sector", nullable = false)
    private String stuSector;

    @Column(name = "stu_cell", nullable = false)
    private String stuCell;

    @Column(name = "stu_village", nullable = false)
    private String stuVillage;

    @Column(name = "street_info", nullable = false)
    private String streetInfo;
}
