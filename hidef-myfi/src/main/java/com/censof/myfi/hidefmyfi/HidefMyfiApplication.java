package com.censof.myfi.hidefmyfi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HidefMyfiApplication extends SpringBootServletInitializer{

	
	
	
	public static void main(String[] args) {
		System.out.println("I'm in B-) ");
		SpringApplication.run(HidefMyfiApplication.class, args);
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HidefMyfiApplication.class);
    }
}
