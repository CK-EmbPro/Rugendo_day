package rw.app.urugendo.day.models.filemanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.app.urugendo.day.models.filemanagement.Enums.EFileSizeType;
import rw.app.urugendo.day.models.filemanagement.Enums.EFileStatus;


import java.util.UUID;

@Entity(name = "files")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "path", nullable = false)
    private String path;
    @Column(name = "url", unique = true, nullable = false)
    private String url;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "size_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EFileSizeType sizeType;

    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EFileStatus status;

}
