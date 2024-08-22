package rw.app.urugendo.day.repositories.notify;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.app.urugendo.day.models.Notifications.Notification;

import java.util.UUID;

@Repository
public interface NotifyRepo extends JpaRepository<Notification, UUID> {
}
