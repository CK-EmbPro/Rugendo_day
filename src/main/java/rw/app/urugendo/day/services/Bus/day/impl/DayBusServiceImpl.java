package rw.app.urugendo.day.services.Bus.day.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Bus.dayBus.DayBus;
import rw.app.urugendo.day.models.Bus.dayBus.dto.CreateDayBusDto;
import rw.app.urugendo.day.models.Bus.dayBus.dto.DayBusDto;
import rw.app.urugendo.day.models.Bus.dayBus.utils.DayBusMapper;
import rw.app.urugendo.day.repositories.Bus.dayBusRepo.DayBusRepo;
import rw.app.urugendo.day.services.Bus.day.DayBusService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class DayBusServiceImpl implements DayBusService {
    private final DayBusRepo dayBusRepo;
    @Override
    public DayBusDto registerDayBus(CreateDayBusDto createDayBusDto) {
        DayBusDto dayBus = null;
        try {
            DayBus createdDayBus = dayBusRepo.save(DayBusMapper.createDayBusDtoToDayBus(createDayBusDto));
            dayBus = DayBusMapper.dayBusToDayBusDto(createdDayBus);
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }
        return dayBus;
    }

    @Override
    public DayBusDto updateDayBus(UUID dayBusId, DayBusDto dayBusDto) {
        DayBusDto updatedDayBus = null;
        try {
            Optional<DayBus> toBeUpdated = dayBusRepo.findById(dayBusId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("school_bus not found");

            toBeUpdated.get().setPlateNo(dayBusDto.getPlateNo());
            toBeUpdated.get().setGps_id(dayBusDto.getGpsId());
            toBeUpdated.get().setN_of_seats(dayBusDto.getNOfSeats());
            toBeUpdated.get().setSchoolId(dayBusDto.getSchoolId());

            DayBus updatedBus = dayBusRepo.save(toBeUpdated.get());
            updatedDayBus =  DayBusMapper.dayBusToDayBusDto(updatedBus);

        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return updatedDayBus;
    }

    @Override
    public List<DayBusDto> getBusesOnSchool(UUID schoolId) {
        List<DayBusDto> schoolBusesDto = null;
        try {
            List<DayBus> schoolBuses = dayBusRepo.findDayBusesBySchoolId(schoolId);
            if (!schoolBuses.isEmpty()){
                schoolBusesDto=  schoolBuses
                        .stream()
                        .map(
                                DayBusMapper::dayBusToDayBusDto
                        )
                        .toList();
            }

        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }
       return schoolBusesDto;
    }

    @Override
    public List<DayBusDto> getAllDayBuses() {
        List<DayBusDto> allDayBuses = null;
        try {
            List<DayBus> allBuses = dayBusRepo.findAll();
            allDayBuses =  allBuses
                    .stream()
                    .map(
                            DayBusMapper::dayBusToDayBusDto
                    )
                    .toList();
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return allDayBuses;
    }

    @Override
    public DayBusDto getSingleDayBus(UUID dayBusId) {
        DayBusDto schoolBusDto = null;
        try {
            Optional<DayBus> schoolBus = dayBusRepo.findById(dayBusId);
            if (schoolBus.isEmpty()) throw new ResourceNotFoundException("school_bus not found");
            schoolBusDto = DayBusMapper.dayBusToDayBusDto(schoolBus.get());
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return schoolBusDto;
    }

    @Override
    public DayBusDto getDayBusByPlateNo(String plateNo) {
        DayBusDto schoolBusDto = null;
        try {
            Optional<DayBus> schoolBus = dayBusRepo.findBusByPlateNo(plateNo);
            if (schoolBus.isEmpty()) throw new ResourceNotFoundException("school_bus not found");
            schoolBusDto = DayBusMapper.dayBusToDayBusDto(schoolBus.get());
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }

        return schoolBusDto;
    }

    @Override
    public boolean deleteDayBus(UUID dayBusId) {
        boolean isDeleted = false;
        try {
            Optional<DayBus> toBeDeleted = dayBusRepo.findById(dayBusId);
            if (toBeDeleted.isEmpty()) throw new ResourceNotFoundException("school_bus not found");
            dayBusRepo.delete(toBeDeleted.get());
            return !dayBusRepo.existsById(dayBusId);
        }catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;

    }
}
