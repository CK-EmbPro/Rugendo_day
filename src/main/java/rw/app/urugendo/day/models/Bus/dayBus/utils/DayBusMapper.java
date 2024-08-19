package rw.app.urugendo.day.models.Bus.dayBus.utils;

import rw.app.urugendo.day.models.Bus.dayBus.DayBus;
import rw.app.urugendo.day.models.Bus.dayBus.dto.CreateDayBusDto;
import rw.app.urugendo.day.models.Bus.dayBus.dto.DayBusDto;

public class DayBusMapper {
    public static DayBusDto dayBusToDayBusDto(DayBus dayBus) {
        return DayBusDto.builder()
                .dayBusId(dayBus.getDayBusId())
                .schoolId(dayBus.getSchoolId())
                .nOfSeats(dayBus.getN_of_seats())
                .plateNo(dayBus.getPlateNo())
                .gpsId(dayBus.getGps_id())
                .build();
    }

    public static DayBus dayBusDtoToDayBus(DayBusDto dayBusDto) {
        return DayBus.builder()
                .dayBusId(dayBusDto.getDayBusId())
                .schoolId(dayBusDto.getSchoolId())
                .n_of_seats(dayBusDto.getNOfSeats())
                .plateNo(dayBusDto.getPlateNo())
                .gps_id(dayBusDto.getGpsId())
                .build();
    }

    public static DayBus createDayBusDtoToDayBus(CreateDayBusDto createDayBusDto) {
        return DayBus.builder()
                .schoolId(createDayBusDto.getSchoolId())
                .n_of_seats(createDayBusDto.getNOfSeats())
                .plateNo(createDayBusDto.getPlateNo())
                .gps_id(createDayBusDto.getGpsId())
                .build();
    }
}
