package rw.app.urugendo.day.services.schoolmanagement.day;

import rw.app.urugendo.models.schoolmanagement.day.dto.CreateDaySchoolDto;
import rw.app.urugendo.models.schoolmanagement.day.dto.DaySchoolDto;

import java.util.UUID;

public interface DaySchoolService {
    DaySchoolDto registerDaySchool(CreateDaySchoolDto createDaySchoolDto);
    DaySchoolDto updateDaySchool(UUID schoolId, DaySchoolDto daySchoolDto);
    DaySchoolDto getSchoolByCode(String schoolCode);
    DaySchoolDto getSchoolById(UUID schoolId);
    boolean deleteSchoolByCode(String schoolCode);
    boolean deleteSchoolById(UUID schoolId);

}
