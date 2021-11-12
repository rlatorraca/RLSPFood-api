package ca.com.rlsp.rlspfoodapi.core.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Setup the Spring Security on the API
 */
//@Configuration
@EnableWebSecurity
public class ResourceServerSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Retira a tela de Login web a inicia a configuracao de autorizacao de acesso nas requisicoes
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
            .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .oauth2ResourceServer() // Habilita um "Resource Server" na API
                .opaqueToken();  // opaqueToken (sem possibilidade de leitura <> do JWT (possivel leitura))
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

    /**
     *  OLD ONE
     * Retira a tela de Login web a inicia a configuracao de autorizacao de acesso nas requisicoes
     * @param http
     * @throws Exception
     */
    /**
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
    */

    /**
     * Memory User Authentication
     */
    /** FEITO PELO "RLSPFood-api-Auth" (Authorization Server)
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
    /**
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
      */

}
