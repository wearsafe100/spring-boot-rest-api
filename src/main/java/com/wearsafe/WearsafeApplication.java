package com.wearsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WearsafeApplication {

	public static void main(String[] args) {

	
        SpringApplication.run(WearsafeApplication.class, args);
	    new testDeadlockExample().main(new String[] {});
        while(true) {
	       System.out.printf("Helll Hello %s", "Hello");	
	       for (int i = 0; i < i+i+1; i++) {
				System.out.println("infinte loop");
			}
	    }

	}

}
