package rw.app.urugendo.day.repositories.Bus.dayBusRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.app.urugendo.day.models.Bus.dayBus.DayBus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DayBusRepo extends JpaRepository<DayBus, UUID> {
    List<DayBus> findDayBusesBySchoolId(UUID schoolId);
    Optional<DayBus> findBusByPlateNo(String plateNo);
}
