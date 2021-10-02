package ca.com.rlsp.rlspfoodapi.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/*
    End Pooint documentation
 */
@Configuration
@EnableOpenApi
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class) // Faz a conexão do BeanValidator do SpringBoot com o SpringFox(OpenApi)
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
                        //.apis(RequestHandlerSelectors.any()) // tudo relacionado a API sera incluido (inlcusive do SpringBoot
                        .apis(RequestHandlerSelectors.basePackage("ca.com.rlsp.rlspfoodapi.api"))
                        .paths(PathSelectors.any())
                        //.paths(PathSelectors.ant("/restaurants/*")) // apenas o que tiver dentro de restaurnt vai ser mostrado
                        .build()
                .apiInfo(rlspApiInfo())
                .tags(new Tag("Cities", "Manage all CRUD about cities"));
    }

    public ApiInfo rlspApiInfo(){
        return new ApiInfoBuilder()
                .title("RLSP FOOD API")
                .description("API for Canada and Maritimes restaurants")
                .version("1.34")
                .contact(new Contact("RLSPFood", "https://www.rlspfood.api.com.ca", "contact@rlspfood.api.com.ca"))
                .build();

    }


    /*
        Mostra os caminhos (path) para servir arquivos estaticos (html, css, js)  do SpringFox API (Ex: HTML page)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("index.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/META-INF/resources/fonts/");
    }
}
