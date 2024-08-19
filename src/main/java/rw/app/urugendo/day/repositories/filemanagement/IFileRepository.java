package rw.app.urugendo.day.repositories.filemanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.app.urugendo.models.filemanagement.file;

import java.util.UUID;
@Repository
public interface IFileRepository extends JpaRepository<file, UUID> {
}
