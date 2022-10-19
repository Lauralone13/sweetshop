package io.everyonecodes.sweet_store.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		    .authorizeRequests()
		    .antMatchers("/products").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .httpBasic();
		
		return http.build();
	}
}
