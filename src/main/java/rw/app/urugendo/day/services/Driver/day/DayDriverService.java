package rw.app.urugendo.day.services.Driver.day;


import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto.AssignedDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto.CreateAssignedDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.CreateRegisteredDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.RegisteredDriverDto;

import java.util.List;
import java.util.UUID;

public interface DayDriverService {
//    Registered_driver service
    RegisteredDriverDto registerRegisteredDriver(CreateRegisteredDriverDto createRegisteredDriverDto);
    RegisteredDriverDto updateRegisteredDriver(UUID driverId, RegisteredDriverDto registeredDriverDto);
    RegisteredDriverDto getRegisteredDriverById(UUID registeredDriverId);
    List<RegisteredDriverDto> getAllRegisteredDriver();
    RegisteredDriverDto getRegisteredDriverByEmail(String email);

    boolean isRegisteredDriverDeleted(UUID registeredDriverId);

//    Assigned_driver service

    AssignedDriverDto registerAssignedDriver(CreateAssignedDriverDto createAssignedDriverDto);
    AssignedDriverDto updateAssignedDriver(UUID assignedDriverId, AssignedDriverDto assignedDriverDto);
    AssignedDriverDto getAssignedDriver(UUID assignedDriverId);
    AssignedDriverDto getAssignedDriverByEmail(String email);
    AssignedDriverDto getAssignedDriverByTicketId(UUID ticketId);
    boolean isAssignedDriverDeleted(UUID assignedDriverId);
}
