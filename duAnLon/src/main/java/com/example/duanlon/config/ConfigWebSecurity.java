package com.example.duanlon.config;

import com.example.duanlon.config.interceptors.JwtAuthentication;
import com.example.duanlon.config.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

public class ConfigWebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    public JwtAuthentication jwtAuthentication() {
        return new JwtAuthentication();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/playApi/**").permitAll()
                .antMatchers("/auth/criminalCase/status/**").hasRole("CHIEF_INSPECTOR")
                .antMatchers("/auth/criminalCase/number/**").hasRole("SENIOR")
                .antMatchers("/auth/criminalCase/username/**").hasRole("INSPECTOR")
                .anyRequest().authenticated();


        http.addFilterBefore((Filter) jwtAuthentication(), UsernamePasswordAuthenticationFilter.class);
    }

}
