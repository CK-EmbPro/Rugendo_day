package rw.app.urugendo.day.services.notifications.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.Notifications.Notification;
import rw.app.urugendo.day.models.Notifications.dto.CreateNotificationDto;
import rw.app.urugendo.day.models.Notifications.dto.NotificationDto;
import rw.app.urugendo.day.models.Notifications.utils.NotificationsMapper;
import rw.app.urugendo.day.repositories.notify.NotifyRepo;
import rw.app.urugendo.day.services.notifications.NotifyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotifyServiceImpl implements NotifyService {
    private final NotifyRepo notifyRepo;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmailId;
    @Override
    public NotificationDto registerNotify(CreateNotificationDto createNotificationDto) {
        NotificationDto notifyDto = null;
        try {
            Notification toBeSaved = NotificationsMapper.createNotificationToNotification(createNotificationDto);
            Notification saved = notifyRepo.save(toBeSaved);
            notifyDto = NotificationsMapper.notificationToNotificationDto(saved);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmailId);
            message.setTo(notifyDto.getSentTo());
            message.setSubject(notifyDto.getTriggeringAction());
            message.setText(notifyDto.getMessage());

            mailSender.send(message);

//            Send email
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return notifyDto;
    }

    @Override
    public NotificationDto updateNotify(UUID notifyId, NotificationDto updatingDto) {
        NotificationDto notifyDto = null;
        try {
            Optional<Notification> toBeUpdated = notifyRepo.findById(notifyId);
            if (toBeUpdated.isEmpty()) throw new ResourceNotFoundException("Notification not found");

            if (toBeUpdated.get().equals(updatingDto)) {
                toBeUpdated.get().setMessage(updatingDto.getMessage());
                toBeUpdated.get().setSentTo(updatingDto.getSentTo());
                toBeUpdated.get().setTriggeringAction(updatingDto.getTriggeringAction());

                Notification saved = notifyRepo.save(toBeUpdated.get());
                notifyDto = NotificationsMapper.notificationToNotificationDto(saved);

                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmailId);
                message.setTo(notifyDto.getSentTo());
                message.setSubject(notifyDto.getTriggeringAction());
                message.setText(notifyDto.getMessage());

                mailSender.send(message);
//                Send email
            }

        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }


        return notifyDto;
    }

    @Override
    public NotificationDto getSingleNotify(UUID notifyId) {
        NotificationDto notifyDto = null;
        try {
            Optional<Notification> notification = notifyRepo.findById(notifyId);
            if (notification.isEmpty()) throw new ResourceNotFoundException("Notification not found");

            notifyDto = NotificationsMapper.notificationToNotificationDto(notification.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return notifyDto;
    }

    @Override
    public List<NotificationDto> getAllNotifies() {
        List<NotificationDto> notifyDtos = new ArrayList<>();
        try {
            List<Notification> notifications = notifyRepo.findAll();
            notifyDtos = notifications
                    .stream()
                    .map(NotificationsMapper::notificationToNotificationDto)
                    .toList();
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return notifyDtos;
    }

    @Override
    public boolean deleteNotify(UUID notifyId) {
        boolean isDeleted = false;
        try {
            Optional<Notification> notification = notifyRepo.findById(notifyId);
            if (notification.isEmpty()) throw new ResourceNotFoundException("Notification not found");

            notifyRepo.delete(notification.get());
            isDeleted = !notifyRepo.existsById(notifyId);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return isDeleted;
    }
}
