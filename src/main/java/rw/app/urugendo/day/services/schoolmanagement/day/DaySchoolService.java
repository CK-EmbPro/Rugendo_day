package rw.app.urugendo.day.services.schoolmanagement.day;

import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestTicketRouteDto;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.CreateDaySchoolDto;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.DaySchoolDto;

import java.util.List;
import java.util.UUID;

public interface DaySchoolService {
    DaySchoolDto registerDaySchool(CreateDaySchoolDto createDaySchoolDto);
    DaySchoolDto updateDaySchool(UUID schoolId, DaySchoolDto daySchoolDto);
//    DaySchoolDto getSchoolByCode(String schoolCode);
    List<DaySchoolDto> getAllSchools();
    DaySchoolDto getSchoolById(UUID schoolId);
//    boolean deleteSchoolByCode(String schoolCode);
    boolean deleteSchoolById(UUID schoolId);

    RequestTicketRouteDto approveRequest (UUID requestId);
    RequestTicketRouteDto rejectRequest(UUID requestId);
}
