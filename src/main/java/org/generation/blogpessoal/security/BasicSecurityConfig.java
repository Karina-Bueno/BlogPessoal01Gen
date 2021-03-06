package org.generation.blogpessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override //sobrescrita de método
	// alias
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { //throws exception = trativa de erro

		auth.userDetailsService(userDetailsService); //quando o metodo a cima for solicitado chamamos o obejto auth

		auth.inMemoryAuthentication().withUser("root").password(passwordEncoder().encode("root"))
				.authorities("ROLE_USER");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { //assim que o método for iniciado, vai instanciar um HttpSecurity
				
	}

}
