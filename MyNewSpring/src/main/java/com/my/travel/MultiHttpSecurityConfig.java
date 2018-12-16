package com.my.travel;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class MultiHttpSecurityConfig {
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { //1
      auth
          .inMemoryAuthentication()
              .withUser("user").password("password").roles("USER").and()
              .withUser("admin").password("password").roles("USER", "ADMIN");
  }

  @Configuration
  @Order(2)                                                       // 2
  public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
	  @Autowired
		private com.my.travel.components.UserDetailServiceImpl userDetailsService; 

		  @Override
		  protected void configure(HttpSecurity http) throws Exception {
			 http.antMatcher("/rest/**").csrf().disable().cors().and().authorizeRequests()
			  .antMatchers(HttpMethod.POST, "/rest/login1").permitAll()
		        .anyRequest().authenticated()
		        .and()
		        // Filter for the api/login requests
		        .addFilterBefore(new LoginFilter("/rest/login1", authenticationManager()),
		                UsernamePasswordAuthenticationFilter.class)
		        // Filter for other requests to check JWT in header
		        .addFilterBefore(new AuthenticationFilter(),
		                UsernamePasswordAuthenticationFilter.class);
		  }
	  
		  @Bean
		  CorsConfigurationSource corsConfigurationSource() {
		      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		      CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Arrays.asList("*"));
				config.setAllowedMethods(Arrays.asList("*"));
				config.setAllowedHeaders(Arrays.asList("*"));
				config.setAllowCredentials(true);
		      config.applyPermitDefaultValues();
		      
		      source.registerCorsConfiguration("/rest/", config);
		      return source;
		}	
		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		}

  }    

  @Configuration  
  @Order(1)    //   4
  public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	  @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Autowired
	    private DataSource dataSource;

	    @Value("${spring.queries.users-query}")
	    private String usersQuery;

	    @Value("${spring.queries.roles-query}")
	    private String rolesQuery;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth)
	            throws Exception {
	        auth.
	                jdbcAuthentication()
	                .usersByUsernameQuery(usersQuery)
	                .authoritiesByUsernameQuery(rolesQuery)
	                .dataSource(dataSource)
	                .passwordEncoder(bCryptPasswordEncoder);
	    }
	    
	    
	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.antMatcher("/**").csrf().disable().cors().and()
			  .authorizeRequests().antMatchers("/").permitAll();}
	        
	        
	   /*     
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.antMatcher("/web/**").
	                authorizeRequests()
	                .antMatchers("/").permitAll()
	                .antMatchers("/web/login").permitAll()
	                .antMatchers("/web/registration").permitAll()
	                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
	                .authenticated().and().csrf().disable().formLogin()
	                .loginPage("/web/login").failureUrl("/login?error=true")
	                .defaultSuccessUrl("/web/main")
	                .usernameParameter("email")
	                .passwordParameter("password")
	                .and().logout()
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/web").and().exceptionHandling()
	                .accessDeniedPage("/access-denied");
	    }*/

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	                .ignoring()
	                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	    }
  }
}