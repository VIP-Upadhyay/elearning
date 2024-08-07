package com.sunsoor;

import com.sunsoor.files.FileProperty;
import jakarta.persistence.Column;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({
		FileProperty.class
})
public class TeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherApplication.class, args);
	}

}
