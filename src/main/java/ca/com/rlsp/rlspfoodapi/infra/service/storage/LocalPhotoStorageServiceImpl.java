package ca.com.rlsp.rlspfoodapi.infra.service.storage;

import ca.com.rlsp.rlspfoodapi.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageServiceImpl implements PhotoStorageService {
    public static final String SYSTEM_CANT_STORAGE_THE_FILE = "System can't storage the file.";
    // Pega propridade existem no application.properties  com o PATH para o arquivo Local
    @Value("${rlspFood.Local.storage.photos.directory}")
    private Path photoDirectory;
    @Override
    public void storage(NewPhoto newPhoto) {
        try {
        // Path para o local que sera armazenado o arquivo
        Path newPath = getFilePath(newPhoto.getNewFIle());

        // Faz a copia do arquivo passado na URL para o Local no disco

            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(newPath));
        } catch (Exception e) {
            throw new StorageException(SYSTEM_CANT_STORAGE_THE_FILE, e);
        }

    }

    private Path getFilePath(String fileName) {
        return photoDirectory.resolve(Path.of(fileName));
    }
}
