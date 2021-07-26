package ca.com.rlsp.rlspfoodapi;

import ca.com.rlsp.rlspfoodapi.infra.repository.customized.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class) // Mostra qual sera a implementacao Padrao do SimpleJpaRepository
public class RlspfoodApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RlspfoodApiApplication.class, args);
    }

}
