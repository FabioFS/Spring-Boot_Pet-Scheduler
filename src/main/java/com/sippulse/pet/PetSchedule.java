package com.sippulse.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sippulse.pet.util.PropertiesUtils;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class PetSchedule {
    public static void main(String[] args) {
    	
    	PropertiesUtils.initProperties();
    	
        SpringApplication.run(PetSchedule.class, args);
//        
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
//		String result = bCryptPasswordEncoder.encode("123admin");
//		System.out.println("My hash " + result);
    }
}
