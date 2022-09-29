package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // To tell spring sec frmwork : following contains customization instrs for
					// authentication + authorization
@Configuration // to enable adding @Bean annotated methods
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	// Add a method to return spring bean : meant for supplying authorization rules
	/*
	 *  HttpSecurity :  allows configuring web based security for specific http requests. By
	 * default it will be applied to all requests, but can be restricted using
	 * requestMatcher(RequestMatcher) or other similar methods.
	 * 
	 */
	@Bean
	public SecurityFilterChain configureAuthorization(HttpSecurity http) throws Exception{
		System.out.println("secutiry configure authorization in security config");
		http.csrf().disable().authorizeRequests().
		antMatchers( "/signin", "/patient/profile/{id}",
				"/patient/profile/changePassword/{id}","/patient/signup1" , "/patient/signup2",
				"/medincharge/profile", "/medincharge/medicines", "/medincharge/addMedicine",
				"/medincharge/updateQty/{id}" , "/medincharge/deleteMed/{id}",
				"/patient/*","/patient/order/{id}",
				"/patient/profile/delete/{id}" ,"/patient/payment/{id}","/patient/orderhistory/{id}",
				 "/patient/walletRecharge/{id}","/patient/wallet/{id}","/medincharge/fetchorders","/medincharge/updateorder/{id}","/medincharge/vieworder/{id}").permitAll().

		anyRequest().authenticated().
		and().
		httpBasic();		
		return http.build();
	}

}
