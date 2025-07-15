package suptech.casa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import lombok.Setter;
import suptech.casa.service.UserService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor @Setter
public class SecurityConfig {
	final UserService userService;

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider dap=new DaoAuthenticationProvider(userService);
		dap.setPasswordEncoder(encoder());
		return dap;
		
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests(auth->auth
			.requestMatchers("/accueil/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/users/**").permitAll()
			.requestMatchers("admin/**").hasRole("ADMIN")
			.requestMatchers("user/**").hasAnyRole("ADMIN","USER")
			.anyRequest().authenticated()
		)
		.httpBasic(withDefaults());
		return http.build();
	}
}
