package rw.app.urugendo.day.services.filemanagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rw.app.urugendo.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.models.filemanagement.Enums.EFileSizeType;
import rw.app.urugendo.models.filemanagement.file;
import rw.app.urugendo.repositories.filemanagement.IFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {
    private final IFileRepository fileRepository;
    @Value("${uploads.directory}")
    private String root;

    public FileServiceImpl(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public file findById(UUID id) throws ResourceNotFoundException {
        Optional<file> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()){
            return fileOptional.get();
        }
        else {
            throw new ResourceNotFoundException("File not found","FILE_NOT_FOUND");
        }
    }

    @Override
    public file createFile(MultipartFile document) throws Exception {
        file file = new file();

        String fileName = generateName(Objects.requireNonNull(document.getOriginalFilename()));
        String documentSizeType = getFileSizeTypeFromFileSize(document.getSize());
        int documentSize = getFormattedFileSizeFromFileSize(document.getSize(), EFileSizeType.valueOf(documentSizeType));

        file.setName(fileName);
        file.setPath(this.save(document, fileName));
        file.setUrl("/uploads/" + fileName);
        file.setType(document.getContentType());
        file.setSize(documentSize);
        file.setSizeType(EFileSizeType.valueOf(documentSizeType));

        return this.fileRepository.save(file);
    }

    @Override
    public Resource load(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return new ByteArrayResource(Files.readAllBytes(path));
    }
    private  String save(MultipartFile file, String filename) throws Exception {
        try {
            Path of = Path.of(root);
            Files.copy(file.getInputStream(), of.resolve(Objects.requireNonNull(filename)));
            return of + "/" + filename;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
   private String getFileSizeTypeFromFileSize(long size) {
       if (size >= (1024L * 1024 * 1024 * 1024))
           return EFileSizeType.TB.toString();
       else if (size >= 1024 * 1024 * 1024)
           return EFileSizeType.GB.toString();
       else if (size >= 1024 * 1024)
           return EFileSizeType.MB.toString();
       else if (size >= 1024)
           return EFileSizeType.KB.toString();
       else
           return EFileSizeType.B.toString();
   }

   private int getFormattedFileSizeFromFileSize(double size, EFileSizeType type) {
       if (type == EFileSizeType.TB)
           return (int) (size / (1024L * 1024 * 1024 * 1024));
       else if (type == EFileSizeType.GB)
           return (int) (size / (1024 * 1024 * 1024));
       else if (type == EFileSizeType.MB)
           return (int) (size / (1024 * 1024));
       else if (type == EFileSizeType.KB)
           return (int) (size / (1024));
       else
           return (int) size;
   }

    public static String generateName(String fileName) {
        int period = fileName.lastIndexOf(".");
        String prefix = fileName.substring(0, period);
        String suffix = fileName.substring(period);
        return (prefix + "-" + LocalDateTime.now().atZone(ZoneOffset.of("+02:00")).toInstant().toEpochMilli() + suffix).replaceAll(" ", "");
    }


}
