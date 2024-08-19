package rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.utils;


import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.RegisteredDriver;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.CreateRegisteredDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.RegisteredDriverDto;

public class RegisteredDriverMapper {
    public static RegisteredDriver createRegisteredDriverToRegisteredDriver(CreateRegisteredDriverDto createRegisteredDriverDto){
        return RegisteredDriver
                .builder()
                .firstName(createRegisteredDriverDto.getFirstName())
                .lastName(createRegisteredDriverDto.getLastName())
                .age(createRegisteredDriverDto.getAge())
                .gender(createRegisteredDriverDto.getGender())
                .email(createRegisteredDriverDto.getEmail())
                .schoolId(createRegisteredDriverDto.getSchoolCode())
                .password(createRegisteredDriverDto.getPassword())
                .build();

    }

    public static RegisteredDriverDto registeredDriverToRegisteredDriverDto(RegisteredDriver registeredDriver){
        return RegisteredDriverDto
                .builder()
                .dayDriverId(registeredDriver.getDayDriverId())
                .firstName(registeredDriver.getFirstName())
                .lastName(registeredDriver.getLastName())
                .age(registeredDriver.getAge())
                .gender(registeredDriver.getGender())
                .email(registeredDriver.getEmail())
                .schoolId(registeredDriver.getSchoolId())
                .password(registeredDriver.getPassword())
                .build();

    }

    public static RegisteredDriver registeredDriverDtoToRegisteredDriver(RegisteredDriverDto registeredDriverDto){
        return RegisteredDriver
                .builder()
                .dayDriverId(registeredDriverDto.getDayDriverId())
                .firstName(registeredDriverDto.getFirstName())
                .lastName(registeredDriverDto.getLastName())
                .age(registeredDriverDto.getAge())
                .gender(registeredDriverDto.getGender())
                .email(registeredDriverDto.getEmail())
                .schoolId(registeredDriverDto.getSchoolId())
                .password(registeredDriverDto.getPassword())
                .build();

    }
}
