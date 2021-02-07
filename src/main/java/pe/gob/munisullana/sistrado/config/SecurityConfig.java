package pe.gob.munisullana.sistrado.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import pe.gob.munisullana.sistrado.filters.JWTAuthorizationFilter;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins= "*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**"
            // other public endpoints of your API may be appended to this array
    };

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, new SecureRandom());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
            .authorizeRequests()
            .antMatchers(AUTH_WHITELIST).permitAll()
            .antMatchers(
                    "/webapp/api/v1/ciudadano/login",
                    "/webapp/api/v1/ciudadano/registro",
                    "/webapp/api/v1/ciudadano/verificar/*",
                    "/common/api/v1/tramite/*",
                    "/backoffice/api/v1/usuario/login").permitAll()
            .antMatchers("/**").authenticated()
            .and()
            .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
            .csrf().disable();
    }
}
