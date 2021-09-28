package ca.com.rlsp.rlspfoodapi.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Habilita o CORS globalmente na aplicacao

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*") // metdoos permitidos (* = todos)
                .allowedOrigins("*") // Origins permitidos (* = todos)
                .maxAge(600); // cache do tempo do preflight do CORS (10 minutos no caso)
    }
}
