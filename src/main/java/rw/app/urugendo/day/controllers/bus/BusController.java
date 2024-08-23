package rw.app.urugendo.day.controllers.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.models.Bus.dayBus.dto.CreateDayBusDto;
import rw.app.urugendo.day.models.Bus.dayBus.dto.DayBusDto;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.Bus.day.impl.DayBusServiceImpl;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/day/bus")
@RequiredArgsConstructor
public class BusController {
    private final DayBusServiceImpl dayBusService;

    @PostMapping
    public ResponseEntity<ApiResponse<DayBusDto>> registerBus(@RequestBody CreateDayBusDto createDayBusDto){
        DayBusDto savedBus = dayBusService.registerDayBus(createDayBusDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(savedBus, "School bus registered successfully", "SUCCESS", HttpStatus.CREATED));

    }

    @PutMapping("{bus_id}")
    public ResponseEntity<ApiResponse<DayBusDto>> registerBus(@PathVariable("bus_id") UUID busId, @RequestBody DayBusDto updatingDto){
        DayBusDto updated = dayBusService.updateDayBus(busId, updatingDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updated, "School bus updated successfully", "SUCCESS", HttpStatus.OK    ));

    }

    @GetMapping("{school_id}")
    public ResponseEntity<ApiResponse<List<DayBusDto>>> getBusesOnSchool(@PathVariable("school_id") UUID schoolId){
        List<DayBusDto> schoolBuses = dayBusService.getBusesOnSchool(schoolId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(schoolBuses, "School buses on this school "+schoolId+" retrieved successfully", "SUCCESS", HttpStatus.OK    ));

    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<DayBusDto>>> getAllDayBuses(){
        List<DayBusDto> schoolBuses = dayBusService.getAllDayBuses();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(schoolBuses, "School buses retrieved successfully", "SUCCESS", HttpStatus.OK    ));

    }


    @GetMapping("{bus_id}")
    public ResponseEntity<ApiResponse<DayBusDto>> getSingleDayBus(@PathVariable("bus_id") UUID busId){
        DayBusDto schoolBus = dayBusService.getSingleDayBus(busId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(schoolBus, "School bus retrieved successfully", "SUCCESS", HttpStatus.OK    ));

    }

    @GetMapping("{plate_no}")
    public ResponseEntity<ApiResponse<DayBusDto>> getDayBusByPlateNo(@PathVariable("plate_no") String plateNo){
        DayBusDto schoolBus = dayBusService.getDayBusByPlateNo(plateNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(schoolBus, "School bus of plateNo: "+plateNo+" retrieved successfully", "SUCCESS", HttpStatus.OK    ));

    }

    @DeleteMapping("{bus_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteDayBus(@PathVariable("bus_id") UUID busId){
        boolean isDeleted = dayBusService.deleteDayBus(busId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "School bus deleted successfully", "SUCCESS", HttpStatus.OK));

    }
}
