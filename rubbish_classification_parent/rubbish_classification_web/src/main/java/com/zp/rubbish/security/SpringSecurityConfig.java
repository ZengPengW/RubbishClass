package com.zp.rubbish.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity //启动Security过滤器链
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	@Value("${ADMINNAME}")
	private  String ADMINNAME;

	@Value("${ANMINPASSWORD}")
	private  String ANMINPASSWORD;

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	//<security:authentication-manager>
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser(ADMINNAME).password(ANMINPASSWORD).authorities("ROLE_ADD_PRODUCT","ROLE_UPDATE_PRODUCT");
		//auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
		auth.inMemoryAuthentication().withUser(ADMINNAME).password(ANMINPASSWORD).roles("zp");

	}
	
	
	//<security:http>
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/adminRubbish","/feedbackEdit","/saveRubbish").hasRole("zp")
		.and().formLogin()//表单模式
		.and().csrf().disable()//关闭esrt
		.headers().frameOptions().sameOrigin();//解决iframe

		
	}
	
	

	
	//密码加密
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
}
