package com.selab.boot.config;

import com.selab.boot.filter.AjaxTimeOutFilter;
import com.selab.boot.service.UserService;
import com.selab.boot.util.JSONWebTokenManager;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import java.security.Key;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Setter(onMethod_ = @Autowired)
	private UserService userService;

	@Setter(onMethod_ = @Autowired)
	private AjaxTimeOutFilter ajaxTimeOutFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
        	.antMatchers("/signIn", "/signInFail", "/accessDenied").permitAll()
        	.antMatchers("/**").authenticated()
        .and()
        	.csrf()
        .and()
            .addFilterAfter(ajaxTimeOutFilter, ExceptionTranslationFilter.class)
            .formLogin()
            .loginPage("/signIn")
            .defaultSuccessUrl("/")
            .failureForwardUrl("/signInFail")
            .usernameParameter("username")
        .and()
            .logout()
            .logoutUrl("/signOut")
            .logoutSuccessUrl("/signIn")
            .invalidateHttpSession(true);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JSONWebTokenManager jwtTokenConfig(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        JSONWebTokenManager JSONWebTokenManager = new JSONWebTokenManager(key);

        return JSONWebTokenManager;
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/vendor/**", "/css/**", "/js/**", "/img/**");
    }

}
