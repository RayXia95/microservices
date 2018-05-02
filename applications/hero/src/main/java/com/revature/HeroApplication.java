package com.revature;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.revature.model.Hero;
import com.revature.service.HeroService;

@SpringBootApplication
@EnableDiscoveryClient
public class HeroApplication implements CommandLineRunner {

	Logger logger = Logger.getLogger(HeroApplication.class);
	
	@Autowired
	ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(HeroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		HeroService heroService = context.getBean(HeroService.class);
		heroService.registerHero(new Hero("Dr. Strange", "Knowledge", true));
		
		//Testing read
		logger.info("Retrieving Dr. Strange: " + heroService.getHero("dr. strange"));
	}
}
