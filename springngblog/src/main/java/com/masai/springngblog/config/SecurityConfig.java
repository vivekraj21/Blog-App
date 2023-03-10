package com.masai.springngblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.masai.springngblog.security.JwtAuthenticationFilter;

import org.springframework.security.config.BeanIds;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	
	private UserDetailsService userDetailsService;
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	 @Override
	    public void configure(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.addFilterAfter(jwtAuthenticationFilter(), BasicAuthenticationFilter.class)
         .authorizeRequests()
         .antMatchers("/api/auth/**")
         .permitAll()
         .antMatchers("/api/posts/all")
         .permitAll()
         .antMatchers("/v2/api-docs",
         		"/configuration/ui",
         		"/swagger-resources/**",
         		"/configuration/security",
         		"/webjars/**")
         .permitAll()
         .antMatchers("/swagger-ui.html").permitAll()
         .antMatchers("/swagger-ui/index.html").permitAll()
         .anyRequest()
         .authenticated()
         .and()
         .csrf().disable();
	    }
	 @Autowired
	    public SecurityConfig(UserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	    }
	    
	    public AuthenticationManagerBuilder configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	        return authenticationManagerBuilder;
	    }

	 
	 @Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
}