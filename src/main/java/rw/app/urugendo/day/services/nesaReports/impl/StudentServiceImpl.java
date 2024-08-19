package rw.app.urugendo.day.services.nesaReports.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.app.urugendo.models.student.boardingStudent.BoardingStudent;
import rw.app.urugendo.models.student.boardingStudent.dto.BoardingStudentDto;
import rw.app.urugendo.models.student.boardingStudent.dto.CreateBoardingStudentDto;
import rw.app.urugendo.models.student.boardingStudent.enums.*;
import rw.app.urugendo.models.student.boardingStudent.utils.BoardingStudentMapper;
import rw.app.urugendo.models.student.enums.*;
import rw.app.urugendo.models.usermanagement.Enums.EGender;
import rw.app.urugendo.repositories.student.boarding.BoardingStudentRepo;
import rw.app.urugendo.services.nesaReports.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final BoardingStudentRepo boardingStudentRepo;
    @Override
    public BoardingStudentDto registerStudent(CreateBoardingStudentDto createStudentDto) {
        BoardingStudent toBeSaved = BoardingStudentMapper.createStudentDtotoToStudent(createStudentDto);
        if(toBeSaved.getRebCombination() == null){
            toBeSaved.setRebCombination(ERebCombination.NONE);
        }
        if(toBeSaved.getTvetTrade() == null){
            toBeSaved.setTvetTrade(ETvetCombination.NONE);
        }
        if(toBeSaved.getStuTvetLevel() ==null){
            toBeSaved.setStuTvetLevel(ETvetLevels.NONE);
        }
        if (toBeSaved.getStuRebClass() == null){
            toBeSaved.setStuRebClass(ERebClass.NONE);
        }

        return BoardingStudentMapper.studentToStudentDto(toBeSaved);
    }

    @Override
    public BoardingStudentDto getSingleStudent(UUID studentId) {
        Optional<BoardingStudent> student = boardingStudentRepo.findStudentByStudentId(studentId);
        if (student.isPresent()){
            return BoardingStudentMapper.studentToStudentDto(student.get());
        }else{
            throw new EntityNotFoundException("student with "+studentId+" not found");
        }
    }

    @Override
    public BoardingStudentDto getStuByTicketAndSeatId(UUID ticketId, UUID seatId) {
        Optional<BoardingStudent> student = boardingStudentRepo.findStudentByTicketIdAndSeatId(ticketId, seatId);
        return BoardingStudentMapper.studentToStudentDto(student.get());
    }

    @Override
    public List<BoardingStudentDto> getAllStudent() {
        List<BoardingStudent> allBoardingStudents = boardingStudentRepo.findAll();
        return BoardingStudentMapper.studentListToStudentDtoList(allBoardingStudents);
    }

    @Override
    public List<BoardingStudentDto> getStudentsByLevel(ELevel level) {
        List<BoardingStudent> studentsByLevel= boardingStudentRepo.findStudentsByLevel(level);
        return BoardingStudentMapper.studentListToStudentDtoList(studentsByLevel);
    }

    @Override
    public List<BoardingStudentDto> getStudentsByGender(EGender gender) {
        List<BoardingStudent> studentsByGender = boardingStudentRepo.findStudentsByStuGender(gender);
        return BoardingStudentMapper.studentListToStudentDtoList(studentsByGender);
    }

    @Override
    public List<BoardingStudentDto> getStudentsBySchoolDistrict(Edistricts districtName) {
        List<BoardingStudent> studentsByDistrict = boardingStudentRepo.findStudentsBySchoolDistrict(districtName);
        return BoardingStudentMapper.studentListToStudentDtoList(studentsByDistrict);
    }
//
//    @Override
//    public List<StudentDto> getStudentsByLevelAndClass(ELevel level, ETvetLevels tvetLevel) {
//        return null;
//    }
//
//    @Override
//    public List<StudentDto> getStudentsByLevelAndClass(ELevel level, ERebClass rebClass) {
//        return null;
//    }
//
//
//
//    @Override
//    public List<StudentDto> getStudentsBySchoolName(String schoolName) {
//        return null;
//    }
//
//    @Override
//    public List<StudentDto> getStusBySchoolDistrictAndSchooolName(Edistricts districtName, String schoolName) {
//        return null;
//    }
//
//    @Override
//    public List<StudentDto> getStusBySchoolDistrictAndClass(Edistricts districtName, ETvetLevels tvetLevel) {
//        return null;
//    }
//
//    @Override
//    public List<StudentDto> getStusBySchoolDistrictAndClass(Edistricts districtName, ERebClass rebClass) {
//        return null;
//    }
}
