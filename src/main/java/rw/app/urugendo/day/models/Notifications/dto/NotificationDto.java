package rw.app.urugendo.day.models.Notifications.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class NotificationDto {
    @NonNull
    private UUID notify_id;

    @NonNull
    private String sentTo;

    @NonNull
    private String message;

    @NonNull
    private String triggeringAction;
}
