package com.sec.mspringsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // cors config
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowCredentials(Boolean.TRUE);
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setMaxAge(3600L);
            return configuration;
        }));
        // csrf config
        http.csrf(AbstractHttpConfigurer::disable);
        // authorize config
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/card").authenticated());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/login/**", "/contactus").permitAll());
        //login page
        http.formLogin(withDefaults());
        // idk
        http.httpBasic(withDefaults());
        // return
        return http.build();
    }
/*
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("nimajan").password("12345").build();
        UserDetails user = User.withUsername("user").password("1234").build();
        return new InMemoryUserDetailsManager(admin, user);

    }
*/

/*
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
