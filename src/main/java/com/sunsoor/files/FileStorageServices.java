package com.sunsoor.files;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorageServices {
    private final Path fileStorageLocation;

    public FileStorageServices(FileProperty fileStorageLocation) {
        super();
        this.fileStorageLocation = Paths.get(fileStorageLocation.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            System.out.println(this.fileStorageLocation+" creating ...");
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String storeFile(MultipartFile file,String filename) {
        LocalDateTime localDateTime = LocalDateTime.now();

        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long dates = zdt.toInstant().toEpochMilli();
        String fileName = StringUtils.cleanPath(dates+filename);

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }
    public boolean deleteFile(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            if(Files.deleteIfExists(filePath)) {
                return true;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}






