package rw.app.urugendo.day.services.filemanagement;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.filemanagement.File;


import java.io.IOException;
import java.util.UUID;

public interface IFileService {
    File findById(UUID id) throws ResourceNotFoundException;
    public File createFile(MultipartFile file) throws Exception;
    public Resource load(String filePath) throws IOException;
}
