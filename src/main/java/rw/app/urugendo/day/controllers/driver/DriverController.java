package rw.app.urugendo.day.controllers.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.CreateRegisteredDriverDto;
import rw.app.urugendo.day.models.Driver.dayDriver.registeredDriver.dto.RegisteredDriverDto;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.Driver.day.impl.DayDriverServiceImpl;
import rw.app.urugendo.day.services.usermanagement.impl.UserServiceImpl;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/day/driver")
@RequiredArgsConstructor
@RestController
public class DriverController {
    private final DayDriverServiceImpl dayDriverService;
    private final UserServiceImpl userService;
    @PostMapping
    public ResponseEntity<ApiResponse<RegisteredDriverDto>> registerRegisteredDriver(@RequestBody CreateRegisteredDriverDto createRegisteredDriverDto){
        RegisteredDriverDto driver = dayDriverService.registerRegisteredDriver(createRegisteredDriverDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(driver, "Created driver successfully", "SUCCESS", HttpStatus.CREATED));
    }

    @PutMapping("{driver_id}")
    public ResponseEntity<ApiResponse<RegisteredDriverDto>> updateRegisteredDriver(@PathVariable("driver_id")UUID driverId, @RequestBody RegisteredDriverDto updatingDto){
        RegisteredDriverDto updated = dayDriverService.updateRegisteredDriver(driverId,updatingDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updated, "Upated driver successfully", "SUCCESS", HttpStatus.OK));
    }

    @GetMapping("{driver_id}")
    public ResponseEntity<ApiResponse<RegisteredDriverDto>> getRegisteredDriverById(@PathVariable("driver_id")UUID driverId){
        RegisteredDriverDto driver = dayDriverService.getRegisteredDriverById(driverId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(driver, "Retrieved driver successfully", "SUCCESS", HttpStatus.OK));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<RegisteredDriverDto>> getRegisteredDriverByEmail(){
        String currentUser = userService.getCurrentUser().getParent().getEmail();
        RegisteredDriverDto driver = dayDriverService.getRegisteredDriverByEmail(currentUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(driver, "Retrieved driver of email "+currentUser+" successfully", "SUCCESS", HttpStatus.OK));
    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse<List<RegisteredDriverDto>>> getAllRegisteredDriver(){
        List<RegisteredDriverDto> drivers = dayDriverService.getAllRegisteredDriver();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(drivers, "Retrieved all drivers successfully", "SUCCESS", HttpStatus.OK));
    }

    @DeleteMapping("{driver_id}")
    public ResponseEntity<ApiResponse<Boolean>> isRegisteredDriverDeleted(@PathVariable("driver_id") UUID driverId){
        boolean isDeleted = dayDriverService.isRegisteredDriverDeleted(driverId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "deleted driver successfully", "SUCCESS", HttpStatus.OK));
    }
}
