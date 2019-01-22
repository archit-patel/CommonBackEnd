package com.common.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/**").permitAll().and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManager) throws Exception {
	    // configure your authentication manager...
	    // this configuration will be used by authenticationManagerBean()
	    // authManager.ldapAuthentication()...
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    // ALTOUGH THIS SEEMS LIKE USELESS CODE,
	    // ITS REQUIRED TO PREVEND SPRING BOOT AUTO-CONFIGURATION
	    return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

}
