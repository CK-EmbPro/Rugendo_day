package rw.app.urugendo.day.services.schoolmanagement.day.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.schoolmanagement.day.DaySchool;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.CreateDaySchoolDto;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.DaySchoolDto;
import rw.app.urugendo.day.models.schoolmanagement.day.utils.DaySchoolMapper;
import rw.app.urugendo.day.repositories.schoolmanagement.day.DaySchoolRepo;
import rw.app.urugendo.day.services.schoolmanagement.day.DaySchoolService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DaySchoolServiceImpl implements DaySchoolService {
    private final DaySchoolRepo schoolRepo;

    @Override
    public DaySchoolDto registerDaySchool(CreateDaySchoolDto createDaySchoolDto) {
        DaySchoolDto registeredSchool = null;
        try {
            DaySchool toBeSaved = DaySchoolMapper.createDaySchoolDtoToDaySchool(createDaySchoolDto);
            DaySchool savedSchool = schoolRepo.save(toBeSaved);
            registeredSchool = DaySchoolMapper.daySchoolToDaySchoolDto(savedSchool);
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return registeredSchool;
    }

    @Override
    public DaySchoolDto updateDaySchool(UUID schoolId, DaySchoolDto daySchoolDto) {
        DaySchoolDto updatedSchoolDto = null;
        try {
            Optional<DaySchool> toBeUpdated = schoolRepo.findById(schoolId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("school not found");
//Updating the school
            toBeUpdated.get().setSchoolName(daySchoolDto.getSchoolName());
            toBeUpdated.get().setSchoolCode(daySchoolDto.getSchoolCode());
            toBeUpdated.get().setSchoolDistrict(daySchoolDto.getSchoolDistrict());
            toBeUpdated.get().setSchoolProvince(daySchoolDto.getSchoolProvince());
            toBeUpdated.get().setSchoolEmail(daySchoolDto.getSchoolEmail());
            toBeUpdated.get().setSchoolPhono(daySchoolDto.getSchoolEmail());


            DaySchool updatedSchool = schoolRepo.save(toBeUpdated.get());
            updatedSchoolDto = DaySchoolMapper.daySchoolToDaySchoolDto(updatedSchool);

        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return updatedSchoolDto;
    }

    @Override
    public DaySchoolDto getSchoolByCode(String schoolCode) {
        DaySchoolDto schoolDto = null;
        try {
            Optional<DaySchool> school = schoolRepo.findDaySchoolBySchoolCode(schoolCode);
            if (school.isEmpty()) throw new ResourceNotFoundException("school not found");
            schoolDto = DaySchoolMapper.daySchoolToDaySchoolDto(school.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return schoolDto;
    }

    @Override
    public List<DaySchoolDto> getAllSchools() {
        List<DaySchoolDto> schoolDtos = new ArrayList<>();
        try {
            List<DaySchool> schools = schoolRepo.findAll();
            schoolDtos = schools
                    .stream()
                    .map(DaySchoolMapper::daySchoolToDaySchoolDto)
                    .toList();
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return schoolDtos;
    }

    @Override
    public DaySchoolDto getSchoolById(UUID schoolId) {
        DaySchoolDto schoolDto = null;
        try {
            Optional<DaySchool> school = schoolRepo.findById(schoolId);
            if (school.isEmpty()) throw new ResourceNotFoundException("school not found");
            schoolDto = DaySchoolMapper.daySchoolToDaySchoolDto(school.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }
        return schoolDto;
    }

    @Override
    public boolean deleteSchoolByCode(String schoolCode) {
        boolean isDeleted = false;
        try {
            Optional<DaySchool> school = schoolRepo.findDaySchoolBySchoolCode(schoolCode);
            if (school.isEmpty()) throw new ResourceNotFoundException("school not found");
            schoolRepo.delete(school.get());
            Optional<DaySchool> isSchoolPresent = schoolRepo.findDaySchoolBySchoolCode(schoolCode);

            isDeleted = isSchoolPresent.isEmpty();
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public boolean deleteSchoolById(UUID schoolId) {
        boolean isDeleted = false;
        try {
            Optional<DaySchool> school = schoolRepo.findById(schoolId);
            if (school.isEmpty()) throw new ResourceNotFoundException("school not found");
            schoolRepo.delete(school.get());
            isDeleted = !schoolRepo.existsById(schoolId);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;
    }
}
