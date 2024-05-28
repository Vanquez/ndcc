package com.kindsonthegenius.fleetmsv2;

import com.kindsonthegenius.fleetmsv2.security.SpringSecurityAuditorAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Fleetmsv2Application extends SpringBootServletInitializer {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new SpringSecurityAuditorAware();
	}

	private int maxUploadSizeInMb= 10 * 1024 * 1024; // 10 MB no usages



	public static void main(String[] args) {
		SpringApplication.run(Fleetmsv2Application.class, args);
	}

//	@EventListener(ApplicationEvent.class)
//	public void sendMail(){
//		senderService.sendEmail("kasubamuchese1@gmail.com",
//				"This is Subject",
//				"This the Body of the Email");
//	}

}
