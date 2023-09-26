package com.myFirstTask.authApi.config;

import com.myFirstTask.authApi.common.JwtTokenFIlter;
import com.myFirstTask.authApi.repo.UserRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserRepo userRepo;
    private final JwtTokenFIlter jwtTokenFilter;

    public WebSecurityConfig(UserRepo userRepo, JwtTokenFIlter jwtTokenFilter) {
        this.userRepo = userRepo;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        System.out.println("managerTest");
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("Nishanth")
//                .password("12345")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").permitAll()

                        .requestMatchers("/user/getUserList").hasRole("USER")
                        .requestMatchers("/admin/test").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        http = http.csrf(csrf-> csrf.disable());
        http = http.cors(cors-> cors.disable());

        return http.build();
    }
}
