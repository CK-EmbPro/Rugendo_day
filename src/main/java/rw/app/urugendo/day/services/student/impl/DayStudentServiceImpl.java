package rw.app.urugendo.day.services.student.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.student.dayStudent.DayStudent;
import rw.app.urugendo.day.models.student.dayStudent.dto.CreateDayStudentDto;
import rw.app.urugendo.day.models.student.dayStudent.dto.DayStudentDto;
import rw.app.urugendo.day.models.student.dayStudent.utils.DayStudentMapper;
import rw.app.urugendo.day.repositories.student.day.DayStudentRepo;
import rw.app.urugendo.day.services.student.DayStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayStudentServiceImpl implements DayStudentService {
    private final DayStudentRepo studentRepo;

    @Override
    public DayStudentDto registerStudent(CreateDayStudentDto createDayStudentDto) {
        DayStudentDto savedStuDto = null;
        try {
            DayStudent toBeSaved = DayStudentMapper.createDayStudentDtotoToDayStudent(createDayStudentDto);
            DayStudent savedStu = studentRepo.save(toBeSaved);
            savedStuDto = DayStudentMapper.dayStudentToDayStudentDto(savedStu);
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return savedStuDto;
    }

    @Override
    public DayStudentDto updateStudent(UUID studentId, DayStudentDto dayStudentDto) {
        DayStudentDto updatedStuDto = null;
        try {
            Optional<DayStudent> toBeUpdated = studentRepo.findById(studentId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("Student not found");
//Updating the student
            toBeUpdated.get().setFirstName(dayStudentDto.getFirstName());
            toBeUpdated.get().setLastName(dayStudentDto.getLastName());
            toBeUpdated.get().setFather_firstName(dayStudentDto.getFather_firstName());
            toBeUpdated.get().setFather_lastName(dayStudentDto.getFather_lastName());
            toBeUpdated.get().setMother_firstName(dayStudentDto.getMother_firstName());
            toBeUpdated.get().setMother_lastName(dayStudentDto.getMother_lastName());
            toBeUpdated.get().setMother_phono(dayStudentDto.getMother_phono());
            toBeUpdated.get().setFather_phono(dayStudentDto.getFather_phono());
            toBeUpdated.get().setSchoolName(dayStudentDto.getSchoolName());
            toBeUpdated.get().setStuProvince(dayStudentDto.getStuProvince());
            toBeUpdated.get().setStuDistrict(dayStudentDto.getStuDistrict());
            toBeUpdated.get().setStuSector(dayStudentDto.getStuSector());
            toBeUpdated.get().setStuCell(dayStudentDto.getStuCell());
            toBeUpdated.get().setStuVillage(dayStudentDto.getStuVillage());
            toBeUpdated.get().setStreetInfo(dayStudentDto.getStreetInfo());

            DayStudent updatedStu = studentRepo.save(toBeUpdated.get());
            updatedStuDto = DayStudentMapper.dayStudentToDayStudentDto(updatedStu);

        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}",e.getMessage());
        }

        return updatedStuDto;

    }

    @Override
    public DayStudentDto getStudentById(UUID studentId) {
        DayStudentDto studentDto = null;
        try {
            Optional<DayStudent> student = studentRepo.findById(studentId);
            if (student.isEmpty()) throw new ResourceNotFoundException("Student not found");
            studentDto = DayStudentMapper.dayStudentToDayStudentDto(student.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}",e.getMessage());
        }

        return studentDto;
    }

//    @Override
//    public List<DayStudentDto> getStudentsBySchoolCode(String schoolCode) {
//        List<DayStudentDto> studentDtos = null;
//        try {
//            List<DayStudent> students = studentRepo.findDayStudentsBySchoolCode(schoolCode);
//            studentDtos = DayStudentMapper.dayStudentListToDayStudentDtoList(students);
//        } catch (Exception e) {
//            log.error("Something bad happened: {}",e.getMessage());
//        }
//        return studentDtos;
//    }

    @Override
    public List<DayStudentDto> getStudentsBySchoolId(UUID schoolId) {
        List<DayStudentDto> studentDtos = new ArrayList<>();
        try {
            List<DayStudent> students = studentRepo.findDayStudentsBySchoolId(schoolId);
            studentDtos = DayStudentMapper.dayStudentListToDayStudentDtoList(students);
        } catch (Exception e) {
            log.error("Something bad happened: {}",e.getMessage());
        }
        return studentDtos;
    }

    @Override
    public boolean deleteStudent(UUID studentId) {
        boolean isDeleted = false;
        try {
            Optional<DayStudent> student = studentRepo.findById(studentId);
            if (student.isEmpty()) throw new ResourceNotFoundException("Student not found");
            studentRepo.delete(student.get());
            isDeleted = !studentRepo.existsById(studentId);
        } catch (ResourceNotFoundException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;
    }
}
