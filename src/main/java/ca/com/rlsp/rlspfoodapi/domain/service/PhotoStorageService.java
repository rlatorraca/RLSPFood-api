package ca.com.rlsp.rlspfoodapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;


public interface PhotoStorageService {

    void storage(NewPhoto newPhoto);

    default String generateUUIDFileName(String orignalName) {
        return UUID.randomUUID().toString() +"__" + orignalName;
    }

    // Classe interna que sera usada para pegarmos o que queremos armazenar nos arquivos no ambiente Local
    @Builder
    @Getter
    class NewPhoto {
        private String newFIle;
        private InputStream inputStream; // Fluxo de leitura do arquivo para foi feito o upload (por meio deste salva-se a foto)
    }
}
