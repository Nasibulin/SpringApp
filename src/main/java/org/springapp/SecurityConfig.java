package org.springapp;

import org.springapp.auth.JWTTokenAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Autowired
//    private AuthEntryPointException unauthorizedHandler;

    @Bean
    public JWTTokenAuthFilter authenticationTokenFilterBean() throws Exception {
        return new JWTTokenAuthFilter();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

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
//                        "/menu/**",
                        "/api/categories/**",
                        "/catalog/**",
                        "/search/**",
                        "/cart/**",
                        "/register/**",
                        "/login/**",
//                        "/order/**",
                        "/images/**",
                        "/styles/**",
                        "/plugins/**", "/css/**", "/js/**"
                ).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/categories/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/api/orders/**").permitAll()
                .antMatchers("/api/menu/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/api/checkout/**").permitAll()
                .antMatchers("/api/order/**").permitAll()
                .anyRequest().authenticated();

        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();

    }

//    @Autowired
//    public AuthUserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService);
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password(bcryptPasswordEncoder().encode("password")).roles("USER");
//    }
}
