package rw.app.urugendo.day.models.schoolmanagement.day.utils;

import rw.app.urugendo.day.models.schoolmanagement.day.DaySchool;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.CreateDaySchoolDto;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.DaySchoolDto;

public class DaySchoolMapper {
    public static DaySchoolDto daySchoolToDaySchoolDto(DaySchool daySchool) {
        return DaySchoolDto.builder()
                .daySchoolId(daySchool.getDaySchoolId())
                .schoolName(daySchool.getSchoolName())
                .schoolCode(daySchool.getSchoolCode())
                .schoolDistrict(daySchool.getSchoolDistrict())
                .schoolProvince(daySchool.getSchoolProvince())
                .schoolEmail(daySchool.getSchoolEmail())
                .schoolPhono(daySchool.getSchoolPhono())
                .build();
    }

    public static DaySchool daySchoolDtoToDaySchool(DaySchoolDto daySchoolDto) {
        return DaySchool.builder()
                .daySchoolId(daySchoolDto.getDaySchoolId())
                .schoolName(daySchoolDto.getSchoolName())
                .schoolCode(daySchoolDto.getSchoolCode())
                .schoolDistrict(daySchoolDto.getSchoolDistrict())
                .schoolProvince(daySchoolDto.getSchoolProvince())
                .schoolEmail(daySchoolDto.getSchoolEmail())
                .schoolPhono(daySchoolDto.getSchoolPhono())
                .build();
    }

    public static DaySchool createDaySchoolDtoToDaySchool(CreateDaySchoolDto createDaySchoolDto) {
        return DaySchool.builder()
                .schoolName(createDaySchoolDto.getSchoolName())
                .schoolCode(createDaySchoolDto.getSchoolCode())
                .schoolDistrict(createDaySchoolDto.getSchoolDistrict())
                .schoolProvince(createDaySchoolDto.getSchoolProvince())
                .schoolEmail(createDaySchoolDto.getSchoolEmail())
                .schoolPhono(createDaySchoolDto.getSchoolPhono())
                .build();
    }
}
