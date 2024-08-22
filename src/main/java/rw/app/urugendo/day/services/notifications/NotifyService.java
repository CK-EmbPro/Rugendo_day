package rw.app.urugendo.day.services.notifications;

import rw.app.urugendo.day.models.Notifications.dto.CreateNotificationDto;
import rw.app.urugendo.day.models.Notifications.dto.NotificationDto;

import java.util.List;
import java.util.UUID;

public interface NotifyService {
    NotificationDto registerNotify(CreateNotificationDto createNotificationDto);
    NotificationDto updateNotify(UUID notifyId, NotificationDto updatingDto);
    NotificationDto getSingleNotify(UUID notifyId);
    List<NotificationDto> getAllNotifies();
    boolean deleteNotify(UUID notifyId);
}
