package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		String key = "jdk.calendar.japanese.supplemental.era";
		if (System.getProperty(key) == null) {
			System.setProperty(key, "name=新元号,abbr=N,since=32503680000000");
		}
		SpringApplication.run(App.class, args);
	}

}
