package rw.app.urugendo.day.controllers.student;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.models.student.dayStudent.dto.CreateDayStudentDto;
import rw.app.urugendo.day.models.student.dayStudent.dto.DayStudentDto;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.student.impl.DayStudentServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/day/student")
public class StudentController {
    private final DayStudentServiceImpl dayStudentService;
    @PostMapping
    public ResponseEntity<ApiResponse<DayStudentDto>> registerStudent(@RequestBody CreateDayStudentDto createDayStudentDto){
        DayStudentDto savedStu = dayStudentService.registerStudent(createDayStudentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(savedStu,"Student created successfully", "SUCCESS", HttpStatus.CREATED));
    }

    @PutMapping("{student_id}")
    public ResponseEntity<ApiResponse<DayStudentDto>> updateStudent(@PathVariable("student_id") UUID studentId, @RequestBody DayStudentDto updatingDto){
        DayStudentDto updatedStu = dayStudentService.updateStudent(studentId,updatingDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updatedStu,"Student updated successfully", "SUCCESS", HttpStatus.OK));
    }

    @GetMapping("{student_id}")
    public ResponseEntity<ApiResponse<DayStudentDto>> getStudentById(@PathVariable("student_id") UUID studentId){
        DayStudentDto student = dayStudentService.getStudentById(studentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(student,"Student "+studentId+" retrieved successfully", "SUCCESS", HttpStatus.OK));
    }

    @GetMapping("{school_id}")
    public ResponseEntity<ApiResponse<List<DayStudentDto>>> getStudentsBySchoolId(@PathVariable("school_id") UUID schoolId){
        List<DayStudentDto> schoolStus = dayStudentService.getStudentsBySchoolId(schoolId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(schoolStus,"Students of school "+schoolId+" retrieved successfully", "SUCCESS", HttpStatus.OK));
    }

    @DeleteMapping("{student_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteStudent(@PathVariable("student_id") UUID studentId){
        boolean isDeleted = dayStudentService.deleteStudent(studentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted,"Students "+studentId+" deleted successfully", "SUCCESS", HttpStatus.OK));
    }
}
