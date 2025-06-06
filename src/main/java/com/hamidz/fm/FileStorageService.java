package com.hamidz.fm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@Slf4j
public class FileStorageService {

    private static final String STORAGE_DIRECTORY = "D:\\Storage";

    public void saveFile(MultipartFile fileToSave) throws IOException {
        if (fileToSave.isEmpty()) {
            throw new NullPointerException("fileToSave is null");
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + fileToSave.getOriginalFilename());
        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Unsupported filename!");
        }
        Files.copy(fileToSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public File getDownloadFile(String fileName) throws FileNotFoundException {
        if (fileName == null) {
            throw new NullPointerException("fileName is null");
        }
        var fileToDownload = new File(STORAGE_DIRECTORY + File.separator + fileName);
        if (!Objects.equals(fileToDownload.getParent(), STORAGE_DIRECTORY)) {
            throw new SecurityException("Unsupported filename!");
        }
        if (!fileToDownload.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }
        return fileToDownload;
    }
}
