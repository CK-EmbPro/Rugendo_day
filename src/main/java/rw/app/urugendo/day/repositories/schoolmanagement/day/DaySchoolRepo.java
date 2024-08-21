package rw.app.urugendo.day.repositories.schoolmanagement.day;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.app.urugendo.day.models.schoolmanagement.day.DaySchool;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DaySchoolRepo extends JpaRepository<DaySchool, UUID> {
    Optional<DaySchool> findDaySchoolBySchoolCode(String schoolCode);
}
