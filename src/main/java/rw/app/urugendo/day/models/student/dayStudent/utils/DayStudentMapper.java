package rw.app.urugendo.day.models.student.dayStudent.utils;


import rw.app.urugendo.models.student.dayStudent.DayStudent;
import rw.app.urugendo.models.student.dayStudent.dto.CreateDayStudentDto;
import rw.app.urugendo.models.student.dayStudent.dto.DayStudentDto;

import java.util.List;

public class DayStudentMapper {
    public static List<DayStudent> dayStudentDtoListToDayStudentsList(List<DayStudentDto> dayStudentDtos){
        return dayStudentDtos
                .stream()
                .map(DayStudentMapper::dayStudentDtoToDayStudent)
                .toList();
    }
    public static DayStudent dayStudentDtoToDayStudent(DayStudentDto dayStudentDto) {
        return DayStudent.builder()
                .studentId(dayStudentDto.getStudentId())
                .schoolId(dayStudentDto.getSchoolId())
                .firstName(dayStudentDto.getFirstName())
                .lastName(dayStudentDto.getLastName())
                .father_firstName(dayStudentDto.getFather_firstName())
                .father_lastName(dayStudentDto.getFather_lastName())
                .mother_firstName(dayStudentDto.getMother_firstName())
                .mother_lastName(dayStudentDto.getMother_lastName())
                .father_phono(dayStudentDto.getFather_phono())
                .mother_phono(dayStudentDto.getMother_phono())
                .schoolName(dayStudentDto.getSchoolName())
                .schoolCode(dayStudentDto.getSchoolCode())
                .stuProvince(dayStudentDto.getStuProvince())
                .stuDistrict(dayStudentDto.getStuDistrict())
                .stuSector(dayStudentDto.getStuSector())
                .stuCell(dayStudentDto.getStuCell())
                .stuVillage(dayStudentDto.getStuVillage())
                .streetInfo(dayStudentDto.getStreetInfo())
                .build();
    }

    public static List<DayStudentDto> dayStudentListToDayStudentDtoList(List<DayStudent> dayStudents){
        return dayStudents
                .stream()
                .map(DayStudentMapper::dayStudentToDayStudentDto)
                .toList();
    }

    public static DayStudentDto dayStudentToDayStudentDto(DayStudent dayStudent) {
        return DayStudentDto.builder()
                .studentId(dayStudent.getStudentId())
                .schoolId(dayStudent.getSchoolId())
                .firstName(dayStudent.getFirstName())
                .lastName(dayStudent.getLastName())
                .father_firstName(dayStudent.getFather_firstName())
                .father_lastName(dayStudent.getFather_lastName())
                .mother_firstName(dayStudent.getMother_firstName())
                .mother_lastName(dayStudent.getMother_lastName())
                .father_phono(dayStudent.getFather_phono())
                .mother_phono(dayStudent.getMother_phono())
                .schoolName(dayStudent.getSchoolName())
                .schoolCode(dayStudent.getSchoolCode())
                .stuProvince(dayStudent.getStuProvince())
                .stuDistrict(dayStudent.getStuDistrict())
                .stuSector(dayStudent.getStuSector())
                .stuCell(dayStudent.getStuCell())
                .stuVillage(dayStudent.getStuVillage())
                .streetInfo(dayStudent.getStreetInfo())
                .build();
    }
    public static DayStudent createDayStudentDtotoToDayStudent(CreateDayStudentDto createDayStudentDto) {
        return DayStudent.builder()
                .firstName(createDayStudentDto.getFirstName())
                .lastName(createDayStudentDto.getLastName())
                .father_firstName(createDayStudentDto.getFather_firstName())
                .father_lastName(createDayStudentDto.getFather_lastName())
                .mother_firstName(createDayStudentDto.getMother_firstName())
                .mother_lastName(createDayStudentDto.getMother_lastName())
                .father_phono(createDayStudentDto.getFather_phono())
                .mother_phono(createDayStudentDto.getMother_phono())
                .schoolName(createDayStudentDto.getSchoolName())
                .schoolCode(createDayStudentDto.getSchoolCode())
                .stuProvince(createDayStudentDto.getStuProvince())
                .stuDistrict(createDayStudentDto.getStuDistrict())
                .stuSector(createDayStudentDto.getStuSector())
                .stuCell(createDayStudentDto.getStuCell())
                .stuVillage(createDayStudentDto.getStuVillage())
                .streetInfo(createDayStudentDto.getStreetInfo())
                .build();
    }
}
