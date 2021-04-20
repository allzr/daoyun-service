package com.fzu.edu.daoyun.config.security;

import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;
    @Autowired
    private  RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired
    private  RestfulAccessDeniedHandler restfulAccessDeniedHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .cacheControl();
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);

    }
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(
                "/user/login*",
                "/user/register",
                "/user/logout",
                "/user/code*",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/css/**",
                "/js/**",
                "/index.html",
                "favicon.ico"
        );
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return phoneNumber->{
            User user = userService.getUserByPhoneNumber(phoneNumber);
            if(null != user)
                return user;
            return null;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        return new JwtAuthencationTokenFilter();
    }
}
