package rw.app.urugendo.day.services.nesaReports;

import rw.app.urugendo.models.student.boardingStudent.dto.BoardingStudentDto;
import rw.app.urugendo.models.student.boardingStudent.dto.CreateBoardingStudentDto;
import rw.app.urugendo.models.student.enums.ELevel;
import rw.app.urugendo.models.student.enums.Edistricts;
import rw.app.urugendo.models.usermanagement.Enums.EGender;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    BoardingStudentDto registerStudent(CreateBoardingStudentDto createStudentDto);
    BoardingStudentDto getSingleStudent(UUID studentId);
    BoardingStudentDto getStuByTicketAndSeatId(UUID ticketId, UUID seatId);
    List<BoardingStudentDto> getAllStudent();
    List<BoardingStudentDto> getStudentsByLevel(ELevel level);
    List<BoardingStudentDto> getStudentsByGender(EGender gender);
    List<BoardingStudentDto> getStudentsBySchoolDistrict(Edistricts districtName);
//    List<StudentDto> getStudentsByLevelAndClass(ELevel level, ETvetLevels tvetLevel);
//    List<StudentDto> getStudentsByLevelAndClass(ELevel level, ERebClass rebClass);
//    List<StudentDto> getStudentsBySchoolName(String schoolName);
//    List<StudentDto> getStusBySchoolDistrictAndSchooolName(Edistricts districtName, String schoolName);
//    List<StudentDto> getStusBySchoolDistrictAndClass(Edistricts districtName, ETvetLevels tvetLevel);
//    List<StudentDto> getStusBySchoolDistrictAndClass(Edistricts districtName, ERebClass rebClass);
}
