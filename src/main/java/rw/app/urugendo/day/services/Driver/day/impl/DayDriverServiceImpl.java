package rw.app.urugendo.day.services.Driver.day.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.AssignedDriver;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto.AssignedDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto.CreateAssignedDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.utils.AssignedDriverMapper;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.RegisteredDriver;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.CreateRegisteredDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.RegisteredDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.utils.RegisteredDriverMapper;
import rw.app.urugendo.day.repositories.Driver.day.assigned.AssignedDayDriverRepo;
import rw.app.urugendo.day.repositories.Driver.day.registered.RegisteredDayDriverRepo;
import rw.app.urugendo.day.services.Driver.day.DayDriverService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayDriverServiceImpl implements DayDriverService {
    private final RegisteredDayDriverRepo registeredDayDriverRepo;
    private final AssignedDayDriverRepo assignedDayDriverRepo;

    @Override
    public RegisteredDriverDto registerRegisteredDriver(CreateRegisteredDriverDto createRegisteredDriverDto) {
        RegisteredDriverDto registeredDriver = null;
        try {
            RegisteredDriver toBeSaved = RegisteredDriverMapper.createRegisteredDriverToRegisteredDriver(createRegisteredDriverDto);
            RegisteredDriver savedSchool = registeredDayDriverRepo.save(toBeSaved);
            registeredDriver = RegisteredDriverMapper.registeredDriverToRegisteredDriverDto(savedSchool);
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return registeredDriver;
    }

    @Override
    public RegisteredDriverDto updateRegisteredDriver(UUID driverId, RegisteredDriverDto registeredDriverDto) {
        RegisteredDriverDto updatedDriverDto = null;
        try {
            Optional<RegisteredDriver> toBeUpdated = registeredDayDriverRepo.findById(driverId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("driver not found");
//Updating the school
            toBeUpdated.get().setFirstName(registeredDriverDto.getFirstName());
            toBeUpdated.get().setLastName(registeredDriverDto.getLastName());
            toBeUpdated.get().setAge(registeredDriverDto.getAge());
            toBeUpdated.get().setGender(registeredDriverDto.getGender());
            toBeUpdated.get().setEmail(registeredDriverDto.getEmail());
            toBeUpdated.get().setSchoolId(registeredDriverDto.getSchoolId());
            toBeUpdated.get().setPassword(registeredDriverDto.getPassword());


            RegisteredDriver updatedDriver = registeredDayDriverRepo.save(toBeUpdated.get());
            updatedDriverDto = RegisteredDriverMapper.registeredDriverToRegisteredDriverDto(updatedDriver);

        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return updatedDriverDto;
    }

    @Override
    public RegisteredDriverDto getRegisteredDriverById(UUID registeredDriverId) {
        RegisteredDriverDto registeredDriverDto = null;

        try {
            Optional<RegisteredDriver> registeredDriver = registeredDayDriverRepo.findById(registeredDriverId);
            if (registeredDriver.isEmpty()) throw new ResourceNotFoundException("driver not found");
            registeredDriverDto = RegisteredDriverMapper.registeredDriverToRegisteredDriverDto(registeredDriver.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return registeredDriverDto;
    }

    @Override
    public List<RegisteredDriverDto> getAllRegisteredDriver() {
        List<RegisteredDriverDto> driverDtos = new ArrayList<>();

        try {
            List<RegisteredDriver> drivers = registeredDayDriverRepo.findAll();
            driverDtos = drivers
                    .stream()
                    .map(RegisteredDriverMapper::registeredDriverToRegisteredDriverDto)
                    .toList();
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return driverDtos;
    }

    @Override
    public RegisteredDriverDto getRegisteredDriverByEmail(String email) {
        RegisteredDriverDto registeredDriverDto = null;

        try {
            Optional<RegisteredDriver> registeredDriver = registeredDayDriverRepo.findRegisteredDriverByEmail(email);
            if (registeredDriver.isEmpty()) throw new ResourceNotFoundException("driver not found");
            registeredDriverDto = RegisteredDriverMapper.registeredDriverToRegisteredDriverDto(registeredDriver.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return registeredDriverDto;
    }

    @Override
    public boolean isRegisteredDriverDeleted(UUID registeredDriverId) {
        boolean isDeleted = false;
        try {
            Optional<RegisteredDriver> toBeDeleted = registeredDayDriverRepo.findById(registeredDriverId);
            if (toBeDeleted.isEmpty()) throw new ResourceNotFoundException("driver not found");
            registeredDayDriverRepo.delete(toBeDeleted.get());
            isDeleted = !registeredDayDriverRepo.existsById(registeredDriverId);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;

    }

//    Assigned driver service implementation

    @Override
    public AssignedDriverDto registerAssignedDriver(CreateAssignedDriverDto createAssignedDriverDto) {
        AssignedDriverDto assignedDriver = null;
        try {
            AssignedDriver toBeSaved = AssignedDriverMapper.createAssignedDriverToAssignedDriver(createAssignedDriverDto);
            AssignedDriver savedSchool = assignedDayDriverRepo.save(toBeSaved);
            assignedDriver = AssignedDriverMapper.assignedDriverToAssignedDriverDto(savedSchool);
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return assignedDriver;
    }

    @Override
    public AssignedDriverDto updateAssignedDriver(UUID assignedDriverId, AssignedDriverDto assignedDriverDto) {
        AssignedDriverDto updatedDriverDto = null;
        try {
            Optional<AssignedDriver> toBeUpdated = assignedDayDriverRepo.findById(assignedDriverId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("assigned driver not found");
//Updating the school
            toBeUpdated.get().setFirstName(assignedDriverDto.getFirstName());
            toBeUpdated.get().setLastName(assignedDriverDto.getLastName());
            toBeUpdated.get().setAge(assignedDriverDto.getAge());
            toBeUpdated.get().setGender(assignedDriverDto.getGender());
            toBeUpdated.get().setEmail(assignedDriverDto.getEmail());
            toBeUpdated.get().setSchoolId(assignedDriverDto.getSchoolId());
            toBeUpdated.get().setTicketId(assignedDriverDto.getTicketId());
            toBeUpdated.get().setCarPlateNo(assignedDriverDto.getCarPlateNo());
            toBeUpdated.get().setPassword(assignedDriverDto.getPassword());

            AssignedDriver updatedDriver = assignedDayDriverRepo.save(toBeUpdated.get());
            updatedDriverDto = AssignedDriverMapper.assignedDriverToAssignedDriverDto(updatedDriver);

        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return updatedDriverDto;
    }

    @Override
    public AssignedDriverDto getAssignedDriver(UUID assignedDriverId) {
        AssignedDriverDto assignedDriverDto = null;

        try {
            Optional<AssignedDriver> assignedDriver = assignedDayDriverRepo.findById(assignedDriverId);
            if (assignedDriver.isEmpty()) throw new ResourceNotFoundException("driver not found");
            assignedDriverDto = AssignedDriverMapper.assignedDriverToAssignedDriverDto(assignedDriver.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return assignedDriverDto;
    }

    @Override
    public AssignedDriverDto getAssignedDriverByEmail(String email) {
        AssignedDriverDto assignedDriverDto = null;

        try {
            Optional<AssignedDriver> assignedDriver = assignedDayDriverRepo.findAssignedDriverByEmail(email);
            if (assignedDriver.isEmpty()) throw new ResourceNotFoundException("driver not found");
            assignedDriverDto = AssignedDriverMapper.assignedDriverToAssignedDriverDto(assignedDriver.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return assignedDriverDto;
    }

    @Override
    public AssignedDriverDto getAssignedDriverByTicketId(UUID ticketId) {
        AssignedDriverDto assignedDriverDto = null;

        try {
            Optional<AssignedDriver> assignedDriver = assignedDayDriverRepo.findAssignedDriverByTicketId(ticketId);
            if (assignedDriver.isEmpty()) throw new ResourceNotFoundException("driver not found");
            assignedDriverDto = AssignedDriverMapper.assignedDriverToAssignedDriverDto(assignedDriver.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return assignedDriverDto;
    }

    @Override
    public boolean isAssignedDriverDeleted(UUID assignedDriverId) {
        boolean isDeleted = false;
        try {
            Optional<AssignedDriver> toBeDeleted = assignedDayDriverRepo.findById(assignedDriverId);
            if (toBeDeleted.isEmpty()) throw new ResourceNotFoundException("assigned driver not found");
            assignedDayDriverRepo.delete(toBeDeleted.get());
            isDeleted = !assignedDayDriverRepo.existsById(assignedDriverId);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;
    }
}
