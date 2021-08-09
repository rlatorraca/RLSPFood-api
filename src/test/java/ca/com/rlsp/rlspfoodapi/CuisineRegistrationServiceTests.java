package ca.com.rlsp.rlspfoodapi;

/*
    TESTE DE INTEGRACAO
 */

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.service.CuisineRegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CuisineRegistrationServiceTests {

    @Autowired
    private CuisineRegistrationService cuisineRegistrationService;

    @Test
    public void given_DoingRegistrationCuisineCorrectly_ThenSuccess(){

        // CENARIO
        Cuisine newCuisine = new Cuisine();
        newCuisine.setName("Testing Cuisine");
        // ACAO
        newCuisine = cuisineRegistrationService.save(newCuisine);

        //VALIDACAO
        assertThat(newCuisine).isNotNull();
        assertThat(newCuisine.getId()).isNotNull();

    }

    @Test
    public void given_RegistrationCuisineNoName_ThenFail(){

        // CENARIO
        Cuisine newCuisine = new Cuisine();
        newCuisine.setName(null);

        // ACAO
        ConstraintViolationException exceptionxpected = Assertions.assertThrows(ConstraintViolationException.class,() ->{
           cuisineRegistrationService.save(newCuisine);
        });  // Espera-se ocorrer um Exception

        //VALIDACAO
        assertThat(exceptionxpected).isNotNull();
        assertThat(exceptionxpected).isInstanceOf(ConstraintViolationException.class);

    }
}
