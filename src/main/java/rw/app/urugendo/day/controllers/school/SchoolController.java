package rw.app.urugendo.day.controllers.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.models.Notifications.dto.CreateNotificationDto;
import rw.app.urugendo.day.models.Notifications.dto.NotificationDto;
import rw.app.urugendo.day.models.Ticket.dayTIcket.requests.dto.RequestTicketRouteDto;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.CreateDaySchoolDto;
import rw.app.urugendo.day.models.schoolmanagement.day.dto.DaySchoolDto;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.schoolmanagement.day.impl.DaySchoolServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/day/school")
@RequiredArgsConstructor
public class SchoolController {
    private final DaySchoolServiceImpl daySchoolService;

    @PostMapping
    public ResponseEntity<ApiResponse<DaySchoolDto>> registerDaySchool(@RequestBody CreateDaySchoolDto createDaySchoolDto){
        DaySchoolDto school = daySchoolService.registerDaySchool(createDaySchoolDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(school, "Registered school successfully", "SUCCESS", HttpStatus.CREATED));

    }

    @PutMapping("{school_id}")
    public ResponseEntity<ApiResponse<DaySchoolDto>> updateDaySchool(@PathVariable("school_id") UUID schoolId, @RequestBody DaySchoolDto updatingDto){
        DaySchoolDto updated = daySchoolService.updateDaySchool(schoolId, updatingDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updated, "Updated school successfully", "SUCCESS", HttpStatus.OK));

    }


    @PutMapping("request/approve/{request_id}")
    public ResponseEntity<ApiResponse<RequestTicketRouteDto>> approveRequest(@PathVariable("request_id") UUID requestId){
        RequestTicketRouteDto approved = daySchoolService.approveRequest(requestId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(approved, "approved the request successfully", "SUCCESS", HttpStatus.OK));

    }

    @PutMapping("request/reject/{request_id}")
    public ResponseEntity<ApiResponse<RequestTicketRouteDto>> rejectRequest(@PathVariable("request_id") UUID requestId){
        RequestTicketRouteDto rejected = daySchoolService.rejectRequest(requestId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(rejected, "rejected the request successfully", "SUCCESS", HttpStatus.OK));

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DaySchoolDto>>> getAllSchools(){
        List<DaySchoolDto> schools = daySchoolService.getAllSchools();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(schools, "Retrieved all schools successfully", "SUCCESS", HttpStatus.OK));

    }

    @GetMapping("{school_id}")
    public ResponseEntity<ApiResponse<DaySchoolDto>> getSchoolById(@PathVariable("school_id")UUID schoolId){
        DaySchoolDto school = daySchoolService.getSchoolById(schoolId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(school, "Retrieved school successfully", "SUCCESS", HttpStatus.OK));

    }

    @DeleteMapping("{school_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteSchoolById(@PathVariable("school_id") UUID schoolId){
        boolean isDeleted = daySchoolService.deleteSchoolById(schoolId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "Deleeted school "+schoolId+" successfully", "SUCCESS", HttpStatus.OK));

    }
}
