package ca.com.rlsp.rlspfoodapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;


public interface PhotoStorageService {

    void storage(NewPhoto newPhoto);

    void remove(String fileName);

    InputStream recovery(String fileName);

    default String generateUUIDFileName(String orignalName, Long restaurantId, Long productId) {
        return UUID.randomUUID().toString() +"_" + restaurantId+ "_"+ + productId +"_"+ orignalName;
    }

    default void switchOrSave(String oldFileName, NewPhoto newPhoto) {
        if(oldFileName != null) {
            this.remove(oldFileName);
        }
        this.storage(newPhoto);
    }


    // Classe interna que sera usada para pegarmos o que queremos armazenar nos arquivos no ambiente Local
    @Builder
    @Getter
    class NewPhoto {
        private String newFIle;
        private InputStream inputStream; // Fluxo de leitura do arquivo para foi feito o upload (por meio deste salva-se a foto)
    }
}
