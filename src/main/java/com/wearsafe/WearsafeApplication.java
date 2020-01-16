package com.wearsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WearsafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WearsafeApplication.class, args);
		new deadlock().main(new String[] {"Nagendra"});
	}

}
