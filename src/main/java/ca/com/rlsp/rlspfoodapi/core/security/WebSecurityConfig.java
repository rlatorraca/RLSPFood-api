package ca.com.rlsp.rlspfoodapi.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Setup the Spring Security on the API
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Retira a tela de Login web a inicia a configuracao de autorizacao de acesso nas requisicoes
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                    .authorizeRequests()
                        .antMatchers("/v1/cuisines/**").permitAll() // permite esse endpoint sem Autenticacao
                        .anyRequest().authenticated() // autoriza qualquer autorizacao autenticada

                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura sessao sem ESTADO (sem cookies)
                .and()
                    .csrf().disable(); // Cross-Site Request Forgery (CSRF) (no more cookies in the API)
    }

    /**
     * Memory User Authentication
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password(passwordEncoder().encode("12345"))
                    .roles("ADMIN")
                .and()
                .withUser("rlsp")
                    .password(passwordEncoder().encode("1234"))
                    .roles("ADMIN");
    }

    /**
     * Password cryptography using BCrypt Encoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
