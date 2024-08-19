package rw.app.urugendo.day.services.Bus.day;

import rw.app.urugendo.day.models.Bus.dayBus.dto.CreateDayBusDto;
import rw.app.urugendo.day.models.Bus.dayBus.dto.DayBusDto;
import java.util.List;
import java.util.UUID;

public interface DayBusService {
    DayBusDto registerDayBus(CreateDayBusDto createDayBusDto);
    DayBusDto updateDayBus(UUID busId, DayBusDto dayBusDto);
    List<DayBusDto> getBusesOnSchool(UUID schoolId);
    List<DayBusDto> getAllDayBuses();
    DayBusDto getSingleDayBus(UUID dayBusId);
    DayBusDto getDayBusByPlateNo(String plateNo);
    boolean deleteDayBus(UUID dayBusId);
}
