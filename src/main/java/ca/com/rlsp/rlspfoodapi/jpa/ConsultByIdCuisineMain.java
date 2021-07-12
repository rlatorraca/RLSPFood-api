package ca.com.rlsp.rlspfoodapi.jpa;

import ca.com.rlsp.rlspfoodapi.RlspfoodApiApplication;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;


public class ConsultByIdCuisineMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(RlspfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ServiceCuisine serviceCuisine = applicationContext.getBean(ServiceCuisine.class);

        Cuisine cuisine = serviceCuisine.listById(1L);

        System.out.println(cuisine.getName());





    }
}
