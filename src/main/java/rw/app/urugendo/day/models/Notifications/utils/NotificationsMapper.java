package rw.app.urugendo.day.models.Notifications.utils;

import rw.app.urugendo.day.models.Notifications.Notification;
import rw.app.urugendo.day.models.Notifications.dto.CreateNotificationDto;
import rw.app.urugendo.day.models.Notifications.dto.NotificationDto;

public class NotificationsMapper {
    public static Notification createNotificationToNotification(CreateNotificationDto createNotificationDto){
        return Notification.builder()
                .message(createNotificationDto.getMessage())
                .sentTo(createNotificationDto.getSentTo())
                .triggeringAction(createNotificationDto.getTriggeringAction())
                .build();
    }

    public static Notification NotificationDtoToNotification(NotificationDto notificationDto){
        return Notification.builder()
                .notify_id(notificationDto.getNotify_id())
                .message(notificationDto.getMessage())
                .sentTo(notificationDto.getSentTo())
                .triggeringAction(notificationDto.getTriggeringAction())
                .build();
    }
    public static NotificationDto notificationToNotificationDto(Notification notification){
        return NotificationDto.builder()
                .notify_id(notification.getNotify_id())
                .message(notification.getMessage())
                .sentTo(notification.getSentTo())
                .triggeringAction(notification.getTriggeringAction())
                .build();
    }
}
