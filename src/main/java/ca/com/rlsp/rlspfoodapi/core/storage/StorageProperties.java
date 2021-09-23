package ca.com.rlsp.rlspfoodapi.core.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@ConfigurationProperties(prefix="rlspfood.storage")
@Getter
@Setter
public class StorageProperties {

    private Local local = new Local();
    private AwsS3 s2 = new AwsS3();

    @Getter
    @Setter
    public class Local {
        private Path photosDirecotry;
    }

    @Getter
    @Setter
    public class AwsS3 {
        private String accessKey;
        private String password;
        private String bucket;
        private String photosDirectory;
        private String region;
    }
}