package ca.com.rlsp.rlspfoodapi;

import static io.restassured.RestAssured.given;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import ca.com.rlsp.rlspfoodapi.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aspectj.lang.annotation.Before;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Levanta um Servidor Web para testar
@TestPropertySource("/application_test.properties") // Para fazer as mudancas necessarias para o DB de tests
public class CuisineAPITestsIT {

    @LocalServerPort // Como estamos usando RANDOM_PORT (porta aleatoria) temos que pegar essa porta para entao fazermos a conexao com o servidor WEB mock
    private int randomPort;


    @Autowired // Adiciona uma instancia do Flyway para podermos usar a mesma massa de dados para cada teste
    private Flyway flyway;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CuisineRepository cuisineRepository;

    /*
        METODO DE CALLBACK
         - Executado antes dos testes de API
     */
    @BeforeEach
    public void setUp(){
       RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); // Quando falar mostrar REQUISICAO e REPOSTA
       RestAssured.port = randomPort;
       RestAssured.basePath = "/cuisines";

       //flyway.migrate();
        
        // Limpa e prapra os dados a cada teste
        databaseCleaner.clearTables();
        prepareDataForTesting();
        
    }

    private void prepareDataForTesting() {

        Cuisine cuisine01 = new Cuisine();
        cuisine01.setName("Canadian");
        cuisineRepository.save(cuisine01);

        Cuisine cuisine02 = new Cuisine();
        cuisine02.setName("American");
        cuisineRepository.save(cuisine02);

        Cuisine cuisine03 = new Cuisine();
        cuisine03.setName("Chinese");
        cuisineRepository.save(cuisine03);
    }

    // Valida o Codigo da Resposta 200 ao buscar todas cozinhas//
    @Test
    public void must_ReturnStatus200_whenQueryAllCuisines(){

        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    // Valida o Codigo da Resposta 201 ao criar uma cozinha
    @Test
    public void must_ReturnStatus201_whenCreatedCuissine(){

        given()
            .body("{ \"nome\":\"Irish\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
            .post()
        .then()
             .statusCode(HttpStatus.CREATED.value());
    }

    // Valida o Corpo da Resposta
    @Test
    public void must_ReturnNineCuisines_whenQueryAllCuisines(){

        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", Matchers.hasSize(3))
            .body("nome", Matchers.hasItems("American", "Chinese"));

    }
}
