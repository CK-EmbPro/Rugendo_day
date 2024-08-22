package rw.app.urugendo.day.models.Notifications;

import jakarta.persistence.*;
import lombok.*;
import rw.app.urugendo.day.models.utils.TimeStampAudit;

import java.util.UUID;

@Entity
@Table(name = "notifications")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends TimeStampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "notify_id", nullable = false)
    private UUID notify_id;

    @Column(name = "sent_to", nullable = false)
    private String sentTo;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "triggering_action", nullable = false)
    private String triggeringAction;

}
