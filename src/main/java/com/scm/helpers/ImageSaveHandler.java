package com.scm.helpers;

import com.scm.entities.Contact;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@Service
public class ImageSaveHandler {

    @Value("${application.file.uploads.photos-output-path}")
    private String fileUploadPath;

    @Value("${application.file.uploads.photos-access-path}")
    private String fileAccessPath;

    public String saveFile(
            @Nonnull MultipartFile sourceFile,
            @Nonnull Contact contact
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = Helper.getEmailOfLoggedInUser(authentication);

        String[] userEmail = contact.getEmail().split("@");
        final String fileUploadSubPath = username;
        return uploadFile(sourceFile, fileUploadSubPath,contact.getId());
    }

    private String uploadFile(
            @Nonnull MultipartFile sourceFile,
            @Nonnull String fileUploadSubPath,
            @Nonnull String contactId
    ) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);

        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                return null;
            }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + separator + contactId + "." + fileExtension;
        String accessPath = fileAccessPath+"/"+fileUploadSubPath+"/"+contactId+"."+fileExtension;

        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            return accessPath;
        } catch (IOException e) {
            System.out.println("File was not saved");
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }

}
