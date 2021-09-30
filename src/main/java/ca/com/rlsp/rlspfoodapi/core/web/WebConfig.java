package ca.com.rlsp.rlspfoodapi.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

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


    /*
        - Introduzido Automaticamente onde se "cacheControle" habilitado
        - Gera o HASH do Body da resposta para o CACHE ETag
        - Verifica se o HASH criado pelo sistema concide com a eTag que esta no atributo do cabecalho "if-None-Match"
        - Se igual retorna 304 (not modified)
*/
    @Bean
    public Filter shallowETagHeaderFilter(){
       return  new ShallowEtagHeaderFilter(); // Gera o HASH do Body da resposta para o CACHE ETag
    }
}
