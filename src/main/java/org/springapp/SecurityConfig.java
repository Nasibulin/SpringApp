package org.springapp;

import org.springapp.auth.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import org.springapp.auth.service.GuestAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private GuestAuthFilter guestAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/","/menu/**", "/categories/**", "/catalog/**", "/search/**", "/cart/**", "/register/**", "/order/**", "/images/**", "/styles/**",
                             "/plugins/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();
        http.formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();

    }

    @Autowired
    public AuthUserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password(bcryptPasswordEncoder().encode("password")).roles("USER");
//    }
}
