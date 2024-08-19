package rw.app.urugendo.day.repositories.student.day;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.app.urugendo.models.student.dayStudent.DayStudent;

import java.util.List;
import java.util.UUID;

public interface DayStudentRepo extends JpaRepository<DayStudent, UUID> {
    List<DayStudent> findDayStudentsBySchoolCode(String schoolCode);
    List<DayStudent> findDayStudentsBySchoolId(UUID schoolId);
}
