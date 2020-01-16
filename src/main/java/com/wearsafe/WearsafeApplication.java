package com.wearsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wearsafe.web.controller.testDeadlockExample;

@SpringBootApplication
public class WearsafeApplication {

	public static void main(String[] args) {

		SpringApplication.run(WearsafeApplication.class, args);
		new deadlock().main(new String[] {"Nagendra"});
	    new testDeadlockExample().main(new String[] {});
		for (int i = 0; i < 1+i*i; i++) {
	    	new testDeadlockExample().main(new String[] {});
		}

	}

}
