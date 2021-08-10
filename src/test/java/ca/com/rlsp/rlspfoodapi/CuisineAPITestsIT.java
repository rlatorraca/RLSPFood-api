package ca.com.rlsp.rlspfoodapi;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Levanta um Servidor Web para testar
public class CuisineAPITestsIT {

    @LocalServerPort // Como estamos usando RANDOM_PORT (porta aleatoria) temos que pegar essa porta para entao fazermos a conexao com o servidor WEB mock
    private int randomPort;

    // Valida o Codigo da Resposta
    @Test
    public void must_ReturnStatus200_whenQueryAllCuisines(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); // Quando falar mostrar REQUISICAO e REPOSTA

        given()
            .basePath("/cuisines")
            .port(randomPort)
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    // Valida o Corpo da Resposta
    @Test
    public void must_ReturnNineCuisines_whenQueryAllCuisines(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); // Quando falar mostrar REQUISICAO e REPOSTA

        given()
            .basePath("/cuisines")
            .port(randomPort)
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", Matchers.hasSize(9))
            .body("nome", Matchers.hasItems("Portuguese", "Haule"));

    }
}
