package ca.com.rlsp.rlspfoodapi.core.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

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
}
