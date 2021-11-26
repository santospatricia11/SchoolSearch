package com.aps.schoolsearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication().withUser("user")
	    	.password(passwordEncoder().encode("pass")).authorities("USER")
	    	.and()
	        .withUser("000.000.000-00").password(passwordEncoder().encode("pass")).authorities("USER");
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
	      .ignoring()
	         .antMatchers("/resources/**"); 
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/")
			.permitAll()
			.antMatchers("/perfil").authenticated()
			.and()
		.formLogin()
		.loginPage("/login")
		.permitAll();/**/
	}
}
