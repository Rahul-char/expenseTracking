package com.ust.passbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.ust.passbook", "com.speedment.jpastreamer"})
public class PassbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassbookApplication.class, args);
	}

}
