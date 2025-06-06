package com.hamidz.fm;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@Service
public class FileStorageService {

    private static final String STORAGE_DIRECTORY = "D:\\Storage";

    public void saveFile(MultipartFile fileToSave) {
        if(fileToSave.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator +
                fileToSave.getOriginalFilename());
        if(!Objects.equals(targetFile.getParentFile(), STORAGE_DIRECTORY)) {
            throw new IllegalArgumentException("Invalid file name");
        }
    }
}
