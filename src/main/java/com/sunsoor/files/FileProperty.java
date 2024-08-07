package com.sunsoor.files;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "files")
public class FileProperty {
	private String uploadDir;

	 public String getUploadDir() {
	     return uploadDir;
	 }

	 public void setUploadDir(String uploadDir) {
	     this.uploadDir = uploadDir;
	 }
}
