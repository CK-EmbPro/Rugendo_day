package rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.utils;

import lombok.*;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.AssignedDriver;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto.AssignedDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.assignedDriver.dto.CreateAssignedDriverDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AssignedDriverMapper {
    public static AssignedDriver createAssignedDriverToAssignedDriver(CreateAssignedDriverDto createAssignedDriverDto){
        return AssignedDriver
                .builder()
                .firstName(createAssignedDriverDto.getFirstName())
                .ticketId(createAssignedDriverDto.getTicketId())
                .lastName(createAssignedDriverDto.getLastName())
                .age(createAssignedDriverDto.getAge())
                .gender(createAssignedDriverDto.getGender())
                .email(createAssignedDriverDto.getEmail())
                .schoolId(createAssignedDriverDto.getSchoolId())
                .carPlateNo(createAssignedDriverDto.getCarPlateNo())
                .password(createAssignedDriverDto.getPassword())
                .build();

    }

    public static AssignedDriverDto assignedDriverToAssignedDriverDto(AssignedDriver assignedDriver){
        return AssignedDriverDto
                .builder()
                .dayDriverId(assignedDriver.getDayDriverId())
                .firstName(assignedDriver.getFirstName())
                .ticketId(assignedDriver.getTicketId())
                .lastName(assignedDriver.getLastName())
                .age(assignedDriver.getAge())
                .gender(assignedDriver.getGender())
                .email(assignedDriver.getEmail())
                .schoolId(assignedDriver.getSchoolId())
                .carPlateNo(assignedDriver.getCarPlateNo())
                .password(assignedDriver.getPassword())
                .build();

    }

    public static AssignedDriver assignedDriverDtoToAssignedDriver(AssignedDriverDto assignedDriverDto){
        return AssignedDriver
                .builder()
                .dayDriverId(assignedDriverDto.getDayDriverId())
                .firstName(assignedDriverDto.getFirstName())
                .ticketId(assignedDriverDto.getTicketId())
                .lastName(assignedDriverDto.getLastName())
                .age(assignedDriverDto.getAge())
                .gender(assignedDriverDto.getGender())
                .email(assignedDriverDto.getEmail())
                .schoolId(assignedDriverDto.getSchoolId())
                .carPlateNo(assignedDriverDto.getCarPlateNo())
                .password(assignedDriverDto.getPassword())
                .build();

    }
}
