/**
 * 
 */
package com.sippulse.pet.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sippulse.pet.security.PermissionEnum;
import com.sippulse.pet.security.RoleEnum;
import com.sippulse.pet.security.jwt.JwtConfigurer;
import com.sippulse.pet.security.jwt.JwtTokenProvider;

/**
 * @author Fábio Figueiredo da Silva
 *
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	/*
	 * Control access by permission
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				//public endpoints
				.antMatchers("/auth/signin").permitAll()
				.antMatchers("/swagger-ui.html**").permitAll()
				.antMatchers(RoleEnum.ROLE_FIND_VISIT_BY_OWNER.getUrl()).permitAll()
				
				//private endpoints
				.antMatchers(RoleEnum.ROLE_OWNER_CRUD.getUrl()).hasAnyAuthority(PermissionEnum.EMPLOYEE.getPermission())
				.antMatchers(RoleEnum.ROLE_PET_CRUD.getUrl()).hasAnyAuthority(PermissionEnum.EMPLOYEE.getPermission())
				.antMatchers(RoleEnum.ROLE_PET_TYPE_CRUD.getUrl()).hasAnyAuthority(PermissionEnum.EMPLOYEE.getPermission())
				.antMatchers(RoleEnum.ROLE_USER_CRUD.getUrl()).hasAnyAuthority(PermissionEnum.EMPLOYEE.getPermission())
				.antMatchers(RoleEnum.ROLE_VISIT_CRUD.getUrl()).hasAnyAuthority(PermissionEnum.EMPLOYEE.getPermission(),PermissionEnum.VET.getPermission())
				.antMatchers(RoleEnum.ROLE_VET_CRUD.getUrl()).hasAnyAuthority(PermissionEnum.EMPLOYEE.getPermission(),PermissionEnum.VET.getPermission())
				.anyRequest().permitAll()
			.and()
			.apply(new JwtConfigurer(tokenProvider));
	}
	

}
