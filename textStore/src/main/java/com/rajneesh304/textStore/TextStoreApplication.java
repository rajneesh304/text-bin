package com.rajneesh304.textStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TextStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextStoreApplication.class, args);
	}

}
