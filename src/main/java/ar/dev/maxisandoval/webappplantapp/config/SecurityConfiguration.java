package ar.dev.maxisandoval.webappplantapp.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(crsf -> crsf.ignoringRequestMatchers(toH2Console())) //Consola h2
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/registro").permitAll()
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/gestorRoles")).hasAuthority("ROL_ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/actualizarRolUsuario")).hasAuthority("ROL_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/plantas"))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login"))
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }
}