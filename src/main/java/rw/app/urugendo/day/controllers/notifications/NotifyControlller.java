package rw.app.urugendo.day.controllers.notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.app.urugendo.day.models.Notifications.dto.CreateNotificationDto;
import rw.app.urugendo.day.models.Notifications.dto.NotificationDto;
import rw.app.urugendo.day.models.utils.ApiResponse;
import rw.app.urugendo.day.services.notifications.impl.NotifyServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/day/notify")
@RequiredArgsConstructor
public class NotifyControlller {
    private final NotifyServiceImpl notifyService;
    @PostMapping
    public ResponseEntity<ApiResponse<NotificationDto>> registerNotify(@RequestBody CreateNotificationDto createNotificationDto){
        NotificationDto notifyDto = notifyService.registerNotify(createNotificationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(notifyDto, "Registered notification successfully", "SUCCESS", HttpStatus.CREATED));

    }

    @PutMapping("{notify_id}")
    public ResponseEntity<ApiResponse<NotificationDto>> updatedNotify(@PathVariable("notify_id")UUID notifyId, @RequestBody NotificationDto updatingDto){
        NotificationDto updated = notifyService.updateNotify(notifyId, updatingDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updated, "Updated notification successfully", "SUCCESS", HttpStatus.OK));

    }

    @GetMapping("{notify_id}")
    public ResponseEntity<ApiResponse<NotificationDto>> getSingleNotify(@PathVariable("notify_id")UUID notifyId){
        NotificationDto notification = notifyService.getSingleNotify(notifyId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(notification, "Retrieved notification successfully", "SUCCESS", HttpStatus.OK));

    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<NotificationDto>>> getAllNotifies(){
        List<NotificationDto> notifications = notifyService.getAllNotifies();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(notifications, "Retrieved all notifications successfully", "SUCCESS", HttpStatus.OK));

    }

    @DeleteMapping("{notify_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteNotify(@PathVariable("notify_id") UUID notifyId){
        boolean isDeleted = notifyService.deleteNotify(notifyId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "Deleeted notification "+notifyId+" successfully", "SUCCESS", HttpStatus.OK));

    }
}
