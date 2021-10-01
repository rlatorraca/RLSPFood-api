package ca.com.rlsp.rlspfoodapi.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/*
    End Pooint documentation
 */
@Configuration
@EnableOpenApi
public class SpringFoxConfig implements WebMvcConfigurer {

    /*
        Docket => classe do SpringFox que representa a configuracao da API para gerar a documentacao com a especificacao
            OpenAPI
     */
    @Bean
    public Docket apiDocker() {
        // instancia o conjunto de servicos que deve ser documentado
        return new
                Docket(DocumentationType.OAS_30)
                    .select() // seleciona os endpoints que serao expostos
                        .apis(RequestHandlerSelectors.any()) // tudo relacionado a API sera incluido
                .build();
    }


    /*
        Mostra os caminhos (path) para servir arquivos estaticos (html, css, js)  do SpringFox API (Ex: HTML page)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("index.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
