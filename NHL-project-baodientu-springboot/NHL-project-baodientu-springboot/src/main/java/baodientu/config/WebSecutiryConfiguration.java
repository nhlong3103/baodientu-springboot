package baodientu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import baodientu.service.IAccountService;

@SuppressWarnings("deprecation")
@Component
@EnableWebSecurity
public class WebSecutiryConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private IAccountService service;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/assignment10/department**").permitAll().
		// cấu hình author
				and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/baodientu-springboot/accounts**",
						"/api/baodientu-springboot/accounts/**")
				.permitAll().and().httpBasic().and().csrf().disable();

//		http.cors().and().authorizeRequests().anyRequest().permitAll().and().httpBasic().and().csrf()
//		.disable();
	}
}
