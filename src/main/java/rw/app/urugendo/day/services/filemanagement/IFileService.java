package rw.app.urugendo.day.services.filemanagement;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import rw.app.urugendo.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.models.filemanagement.file;

import java.io.IOException;
import java.util.UUID;

public interface IFileService {
    file findById(UUID id) throws ResourceNotFoundException;
    public file createFile(MultipartFile file) throws Exception;
    public Resource load(String filePath) throws IOException;
}
