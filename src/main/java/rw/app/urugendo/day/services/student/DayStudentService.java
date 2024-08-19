package rw.app.urugendo.day.services.student;

import rw.app.urugendo.models.student.dayStudent.dto.CreateDayStudentDto;
import rw.app.urugendo.models.student.dayStudent.dto.DayStudentDto;

import java.util.List;
import java.util.UUID;

public interface DayStudentService {
    DayStudentDto registerStudent(CreateDayStudentDto createDayStudentDto);
    DayStudentDto updateStudent(UUID studentId, DayStudentDto dayStudentDto);
    DayStudentDto getStudentById(UUID studentId);
    List<DayStudentDto> getStudentsBySchoolCode(String schoolCode);
    List<DayStudentDto> getStudentsBySchoolId(UUID schoolId);
    boolean deleteStudent(UUID studentId);
}
