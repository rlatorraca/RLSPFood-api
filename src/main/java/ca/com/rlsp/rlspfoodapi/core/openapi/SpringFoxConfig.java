package ca.com.rlsp.rlspfoodapi.core.openapi;

import net.sf.jasperreports.data.http.RequestMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
    End Pooint documentation
 */
@Configuration
@EnableOpenApi
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class) // Faz a conexão do BeanValidator do SpringBoot com o SpringFox(OpenApi)
public class SpringFoxConfig implements WebMvcConfigurer {


    private static final String MSG_INTERNAL_SERVER_ERROR = "Internal Server Error" ;
    private static final String MSG_NOT_ACCEPTABLE = "Resource may not be acceptable by Consumer";
    private static final String MSG_BAD_REQUEST = "Invalid Request (client error)";
    private static final String MSG_UNSUPPORTED_MEDIA_TYPE = "Request denied. Unsupported format";

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
                .useDefaultResponseMessages(false) // Desabilita os codigo de resposta Standard para ERRORs
                .globalResponses(HttpMethod.GET, globalMsgErrorResponseMessagesToGET()) // Customized Msgs de ERROR para o GET
                .globalResponses(HttpMethod.POST, globalMsgErrorResponseMessagesToPOST()) // Customized Msgs de ERROR para o GET
                .globalResponses(HttpMethod.PUT, globalMsgErrorResponseMessagesToPUT()) // Customized Msgs de ERROR para o GET
                .globalResponses(HttpMethod.DELETE, globalMsgErrorResponseMessagesToDELETE()) // Customized Msgs de ERROR para o GET
                .tags(new Tag("Cities", "Manage all CRUD about cities"));
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

    private ApiInfo rlspApiInfo(){
        return new ApiInfoBuilder()
                .title("RLSP FOOD API")
                .description("API for Canada and Maritimes restaurants")
                .version("1.34")
                .contact(new Contact("RLSPFood", "https://www.rlspfood.api.com.ca", "contact@rlspfood.api.com.ca"))
                .build();

    }

    private List<Response> globalMsgErrorResponseMessagesToGET() {
        return Arrays.asList(
            new ResponseBuilder()
                    .code(toString(HttpStatus.INTERNAL_SERVER_ERROR))
                    .description(MSG_INTERNAL_SERVER_ERROR)
                    //.representation(MediaType.APPLICATION_JSON)
                    //.apply(ApiHandleProblemDetailBuilder())
                    .build(),
            new ResponseBuilder()
                    .code(toString(HttpStatus.NOT_ACCEPTABLE))
                    .description(MSG_NOT_ACCEPTABLE)
                    //.representation(MediaType.APPLICATION_JSON)
                    //.apply(ApiHandleProblemDetailBuilder())
                    .build()
        );
    }

    private List<Response> globalMsgErrorResponseMessagesToPOST() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(toString(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description(MSG_INTERNAL_SERVER_ERROR)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(toString(HttpStatus.NOT_ACCEPTABLE))
                        .description(MSG_NOT_ACCEPTABLE)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(toString(HttpStatus.BAD_REQUEST))
                        .description(MSG_BAD_REQUEST)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(toString(HttpStatus.UNSUPPORTED_MEDIA_TYPE))
                        .description(MSG_UNSUPPORTED_MEDIA_TYPE)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build()
        );
    }

    private List<Response> globalMsgErrorResponseMessagesToPUT() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(toString(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description(MSG_INTERNAL_SERVER_ERROR)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(toString(HttpStatus.NOT_ACCEPTABLE))
                        .description(MSG_NOT_ACCEPTABLE)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(toString(HttpStatus.BAD_REQUEST))
                        .description(MSG_BAD_REQUEST)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(toString(HttpStatus.UNSUPPORTED_MEDIA_TYPE))
                        .description(MSG_UNSUPPORTED_MEDIA_TYPE)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build()
        );
    }

    private List<Response> globalMsgErrorResponseMessagesToDELETE() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(toString(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description(MSG_INTERNAL_SERVER_ERROR)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(toString(HttpStatus.BAD_REQUEST))
                        .description(MSG_BAD_REQUEST)
                        //.representation(MediaType.APPLICATION_JSON)
                        //.apply(ApiHandleProblemDetailBuilder())
                        .build()
        );
    }

    private Consumer<RepresentationBuilder> ApiHandleProblemDetailBuilder() {
        return r -> r.model(
                   m -> m.name("ProblemDetail").referenceModel(
                        ref -> ref.key(
                                k -> k.qualifiedModelName(
                                        q -> q.name("ProblemDetail")
                                                .namespace("ca.com.rlsp.rlspfoodapi.api.exceptionhandler")
                                ))));
    }

    private String toString(HttpStatus httpStatus) {
        return String.valueOf(httpStatus.value());
    }
}
