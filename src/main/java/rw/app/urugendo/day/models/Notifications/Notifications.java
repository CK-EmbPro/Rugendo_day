package rw.app.urugendo.day.models.Notifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "notify_id", nullable = false)
    private UUID notify_id;

    @Column(name = "sent_to", nullable = false)
    private String sentTo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "triggered_by", nullable = false)
    private String triggeredBy;

}
