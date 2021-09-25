package ca.com.rlsp.rlspfoodapi.core.storage;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    Como o AWS S3 JDK e uma API terceira (nao tem qualquer conexao com o Spring Boot) iremos
    fazer uma configuracao para que possamos torna-la um Bean do Spring Boot
 */
@Configuration
public class AwsS3Config {

    // Usado para injetar as propriedades da conta na AWS S3
    @Autowired
    private StorageProperties storageProperties;

    @Bean // Produz uma instancia do AWS S3
    public AmazonS3 amazonS3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(
            storageProperties.getS3().getAccessKey(),
            storageProperties.getS3().getPassword()
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }
}
