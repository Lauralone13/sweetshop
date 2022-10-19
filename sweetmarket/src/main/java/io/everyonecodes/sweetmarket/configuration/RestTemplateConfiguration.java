package io.everyonecodes.sweetmarket.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
	
	private final String name;
	private final String password;
	
	public RestTemplateConfiguration(
			@Value("${sweetshop.store.authentication.name}") String name,
			@Value("${sweetshop.store.authentication.password}") String password) {
		this.name = name;
		this.password = password;
	}
	
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplateBuilder()
				.basicAuthentication(name, password)
				.build();
	}
}
