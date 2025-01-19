package com.nsi.whatsappclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WhatsAppCloneApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsAppCloneApiApplication.class, args);
	}

}
