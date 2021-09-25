package ca.com.rlsp.rlspfoodapi.domain.service;

import lombok.*;

import java.util.Set;

public interface SendEmailService {

    void send(Message message);

    @Getter
    @Builder
    class Message {

        @Singular // Singulariza a Collectio SET<?>
        private Set<String> destinations;
        @NonNull
        private String subject;
        @NonNull
        private String body;

    }
}
