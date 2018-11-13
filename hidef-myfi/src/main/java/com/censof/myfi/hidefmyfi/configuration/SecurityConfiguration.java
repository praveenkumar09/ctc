package com.censof.myfi.hidefmyfi.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;

	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	 @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll()
				//.antMatchers("/admin/**").permitAll()
				//.antMatchers("/cbc/**").permitAll()
				.antMatchers("/registrationEmail").permitAll()
				.antMatchers("/forgotPassword").permitAll()
				.antMatchers("/registrationConfirm").permitAll()
				.antMatchers("/resetPassword").permitAll()
				 .and().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/admin/home")
				.usernameParameter("myCBCId")
				.passwordParameter("password")
				.and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout")
                )
                .addLogoutHandler(new TaskImplementingLogoutHandler())
                .logoutSuccessUrl("/login").permitAll()
                .clearAuthentication(true)
                .invalidateHttpSession(true);
				
		super.configure(http);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		 web
	     .ignoring()
	     .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/WEB-INF/**","/webjars/**");
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	 @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	    }

}