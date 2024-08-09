package bg.softuni.onlinepharmacynews.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity httpSecurity,
      JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(HttpMethod.GET, "/news/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/news/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/news/**").hasRole("ADMIN")
                    .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                    .anyRequest().authenticated()
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();

  }

  @Bean
  public AuthenticationProvider noopAuthenticationProvider() {
    return new AuthenticationProvider() {
      @Override
      public Authentication authenticate(Authentication authentication)
          throws AuthenticationException {
        return null;
      }

      @Override
      public boolean supports(Class<?> authentication) {
        return false;
      }
    };
  }
}
