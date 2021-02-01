package com.example.ISAISA;

import org.joda.time.LocalTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IsaisaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaisaApplication.class, args);
		System.out.println(LocalTime.now());
	}



}
