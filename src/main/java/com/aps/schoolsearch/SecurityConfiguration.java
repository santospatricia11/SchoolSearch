package com.aps.schoolsearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aps.schoolsearch.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsService();
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	    auth
	    	.userDetailsService(userDetailsService())
	    	.passwordEncoder(passwordEncoder());
	    
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
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/cadastrar-usuario", "/cadastrar-usuario/**").permitAll()
			.antMatchers("/perfil", "/perfil/**")
				.hasAuthority("USER")
			.antMatchers("/cadastrar-escola")
				.hasAuthority("USER")
		.and()
			.formLogin(
				form -> 
					form
					.loginPage("/login")
					.defaultSuccessUrl("/")
					.failureUrl("/login?error=true")
					
			)
			.logout(logout ->
					logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
					.invalidateHttpSession(true))
			;/**/
	}
}
