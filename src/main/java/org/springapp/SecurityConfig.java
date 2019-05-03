package org.springapp;

import org.springapp.auth.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import org.springapp.auth.service.GuestAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private GuestAuthFilter guestAuthFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                        // Allow access public resource
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/favicon.ico",
                        "/fragments/**",
                        "/views/**",
                        "/menu/**",
                        "/categories/**",
                        "/catalog/**",
                        "/search/**",
                        "/cart/**",
                        "/register/**",
                        "/order/**",
                        "/images/**",
                        "/styles/**",
                "/plugins/**", "/css/**", "/js/**"
                ).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/categories/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/cart/**").permitAll()
                .antMatchers("/orders/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/checkout/**").permitAll()
                .antMatchers("/order/**").permitAll()
                .anyRequest().authenticated();

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
