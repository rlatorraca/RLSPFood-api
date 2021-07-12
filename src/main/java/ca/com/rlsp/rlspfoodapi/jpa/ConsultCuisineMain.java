package ca.com.rlsp.rlspfoodapi.jpa;

import ca.com.rlsp.rlspfoodapi.RlspfoodApiApplication;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;


public class ConsultCuisineMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(RlspfoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ServiceCuisine serviceCuisine = applicationContext.getBean(ServiceCuisine.class);

        List<Cuisine> cuisines = serviceCuisine.listAll();

        for(Cuisine c : cuisines){
            System.out.println(c.getName());
        }




    }
}
