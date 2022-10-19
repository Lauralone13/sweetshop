package io.everyonecodes.sweetmarket.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@ConfigurationProperties("")
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		    .authorizeRequests()
		    .antMatchers(GET, "/products").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .httpBasic();
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder, CustomerRepository customerRepository, @Value("${sweetshop.market.user.admin.username}") String username, @Value("${sweetshop.market.user.admin.password}") String password, @Value("${sweetshop.market.user.admin.authorities}") String[] authoritiesAdmin, Set<String> authoritiesCustomer) {
//		List<UserDetails> users = new ArrayList<>();
//
//		for(Customer customer : customerRepository.findAll()) {
//			UserDetails user = User.withUsername(customer.getUsername()).password(encoder.encode(customer.getPassword())).authorities((GrantedAuthority) authoritiesCustomer).build();
//			users.add(user);
//		}
//		UserDetails admin = User.withUsername(username)
//		                        .password(encoder.encode(password))
//		                        .authorities(authoritiesAdmin)
//		                        .build();
//		users.add(admin);
//		return new InMemoryUserDetailsManager(users);
//	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
}
