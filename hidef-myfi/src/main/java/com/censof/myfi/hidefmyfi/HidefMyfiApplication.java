package com.censof.myfi.hidefmyfi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import com.censof.myfi.hidefmyfi.controller.UserCheckerJobController;

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
	
	@Bean
	public JobDetail userCheckerJobControllerDetail() {
		String cbcId = fetchProperties("cbcId");
		return JobBuilder.newJob(UserCheckerJobController.class).withIdentity("userCheckerJobController")
				.usingJobData("myCbcId",cbcId).storeDurably().build();
	}

	@Bean
	public Trigger userCheckerJobControllerTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(300).repeatForever();

		return TriggerBuilder.newTrigger().forJob(userCheckerJobControllerDetail())
				.withIdentity("userCheckerJobControllerTrigger").withSchedule(scheduleBuilder).build();
	}
	
	public String fetchProperties(String propertyName) {
		Properties properties = new Properties();
		String value = null;
		try {
			File file = ResourceUtils.getFile("classpath:application.properties");
			InputStream in = new FileInputStream(file);
			properties.load(in);
			value = properties.getProperty(propertyName);
		} catch (IOException e) {
		}
		return value;
	}
}
