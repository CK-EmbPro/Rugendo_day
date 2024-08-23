package rw.app.urugendo.day.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.models.usermanagement.dto.LogInDetailsDTO;
import rw.app.urugendo.day.models.usermanagement.dto.LoginDTO;
import rw.app.urugendo.day.models.usermanagement.dto.UserRegistrationDTO;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.usermanagement.impl.UserServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/day/user")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<ApiResponse<LogInDetailsDTO>> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        LogInDetailsDTO loggedIn = userService.registerUser(userRegistrationDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(loggedIn, "User registered successfully", "SUCCESS", HttpStatus.CREATED));

    }

    @GetMapping
    public ResponseEntity<ApiResponse<LogInDetailsDTO>> login(@RequestBody LoginDTO loginDTO){
        LogInDetailsDTO loggedIn = userService.login(loginDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(loggedIn, "Logged in successfully registered successfully", "SUCCESS", HttpStatus.CREATED));

    }
}
