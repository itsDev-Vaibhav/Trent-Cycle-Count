package com.tatatrent.cyclecount.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.tatatrent.cyclecount.jdbc.config.JdbcDataSource;

@Configuration
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private JdbcDataSource jdbcDataSource;
	
//	final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        	.passwordEncoder(new BCryptPasswordEncoder())
        	.dataSource(jdbcDataSource.dataSource())
            .authoritiesByUsernameQuery("SELECT USERNAME,AUTHORITY FROM WMSTAGE.TL_WMS_RFID_USERS WHERE WMSTAGE.TL_WMS_RFID_USERS.USERNAME=?")
            .usersByUsernameQuery("SELECT USERNAME,PASSWORD,ENABLED FROM WMSTAGE.TL_WMS_RFID_USERS WHERE WMSTAGE.TL_WMS_RFID_USERS.USERNAME=?");
    }


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .httpBasic()// it indicate basic authentication is requires
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/trent_api/v1/process_cycle_count/message").permitAll() // /index will be accessible directly, no need of any authentication
        .anyRequest().authenticated();    // it's indicate all request will be secure
		http.csrf().disable();
	}
	
	
	
	
	

}
