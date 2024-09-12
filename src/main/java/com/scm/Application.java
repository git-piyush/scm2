package com.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableJpaAuditing
@SpringBootApplication
@EnableFeignClients
public class Application {

//	@Autowired
//	private TwilioConfig twilioConfig;
//
//	@PostConstruct
//	public void initTwilio(){
//		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
