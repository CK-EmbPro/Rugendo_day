package rw.app.urugendo.day.repositories.student.day;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.app.urugendo.day.models.student.dayStudent.DayStudent;
import rw.app.urugendo.day.models.usermanagement.Enums.EGender;

import java.util.List;
import java.util.UUID;

public interface DayStudentRepo extends JpaRepository<DayStudent, UUID> {
    List<DayStudent> findDayStudentsByStuGender(EGender gender);
    List<DayStudent> findDayStudentsBySchoolId(UUID schoolId);
}
