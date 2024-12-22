package com.hakan.api.config;

import com.hakan.api.jwt.JwtAuthenticationFilter;
import com.hakan.api.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final AppUserService appUserService;

    @Autowired
    public SpringSecurityConfig(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // CSRF korumasını devre dışı bırakıyoruz, ancak üretim ortamında dikkatli olun!
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/user/create").permitAll() // Bu URL'lere herkes erişebilir
                        .anyRequest().authenticated()  // Diğer tüm URL'lere kimlik doğrulaması gereklidir
                ).addFilterBefore(new JwtAuthenticationFilter(appUserService), UsernamePasswordAuthenticationFilter.class);

        return http.build();  // Yapılandırmayı geri döndürüyoruz
    }
}