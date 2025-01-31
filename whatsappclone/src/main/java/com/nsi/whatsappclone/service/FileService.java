package com.nsi.whatsappclone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 4:42 p.m.
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    @Value("${application.file.uploads.media-output.path}")
    private String fileUploadPath;

    public String saveFile(@NonNull MultipartFile sourceFile, @NonNull String userId) {
        final String fileUploadSubPath = "users" + separator + userId;
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile sourceFile, @NonNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);

        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.error("Error while creating target folder {}", targetFolder);
                return null;
            }
        }

        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        final String targetFilePath = finalUploadPath + separator + System.currentTimeMillis() + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            log.info("File saved to {}", targetPath);
        } catch (Exception e) {
            log.error("Error while saving file", e);
        }
        return null;
    }

    private String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return "";
        }
        int lastDotIndex = originalFilename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return originalFilename.substring(lastDotIndex + 1).toLowerCase();
    }
}
