package com.example.java6.config;


import com.example.java6.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl customerDetailsService;



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        log.info("hello ");
        auth.userDetailsService(customerDetailsService).passwordEncoder(new BCryptPasswordEncoder());


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/home", "/login", "/logout", "/register").permitAll();

        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
        http.authorizeRequests().antMatchers("/customer/**").access("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')");

        http.authorizeRequests().antMatchers("/restaurant/**").access("hasAnyRole('ROLE_RESTAURANT', 'ROLE_ADMIN')");

        http.authorizeRequests().antMatchers("/shipper/**").access("hasAnyRole('ROLE_SHIPPER', 'ROLE_ADMIN')");

        // Trang chỉ dành cho ADMIN
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
//		loginPage ()  - trang đăng nhập tùy chỉnh
//		loginProcessingUrl () - URL để gửi tên người dùng và mật khẩu đến
//		defaultSuccessUrl () - trang đích sau khi đăng nhập thành công
//		failUrl () - trang đích sau khi đăng nhập không thành công
//		logoutUrl () - đăng xuất tùy chỉnh
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginPage("/login")//
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .defaultSuccessUrl("/", true)//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");


        // Cấu hình Remember Me.
//		http.authorizeRequests().and() //
//				.rememberMe().tokenRepository(this.persistentTokenRepository()) //
//				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//		db.setDataSource(dataSource);
//		return db;
//	}

//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//	    return memory;
//	}

}
