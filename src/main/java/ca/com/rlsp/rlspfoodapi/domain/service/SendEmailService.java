package ca.com.rlsp.rlspfoodapi.domain.service;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public interface SendEmailService {

    void send(Message message);

    @Getter
    @Setter
    class Message {
        private Set<String> destination;
        private String subject;
        private String body;

    }
}
