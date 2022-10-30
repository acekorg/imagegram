package com.aleksandar.imagegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RestConfiguration.class)
public class ImagegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagegramApplication.class, args);
	}

}
