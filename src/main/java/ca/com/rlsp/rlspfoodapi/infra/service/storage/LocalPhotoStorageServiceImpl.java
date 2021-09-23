package ca.com.rlsp.rlspfoodapi.infra.service.storage;

import ca.com.rlsp.rlspfoodapi.core.storage.StorageProperties;
import ca.com.rlsp.rlspfoodapi.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageServiceImpl implements PhotoStorageService {

    public static final String SYSTEM_CANT_STORAGE_THE_FILE = "System can't storage the file.";
    public static final String SYSTEM_CANT_DELETE_THE_FILE = "System can't remove the file.";
    public static final String SYSTEM_CANT_RECOVERY_FILE_FROM_STORAGE = "System can't recovery file from Stora.";

    // Pega propridade existem no application.properties  com o PATH para o arquivo Local
    /*New way*/
    @Autowired
    private StorageProperties storageProperties;

    /* Old Way
    @Value("${rlspfood.storage.Local.photos-directory}")
    private Path photoDirectory;
    */

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

    @Override
    public void remove(String fileName) {
        // Path para o local que sera armazenado o arquivo
        try {
            Path newPath = getFilePath(fileName);
            Files.deleteIfExists(newPath);
        } catch (Exception e) {
            throw new StorageException(SYSTEM_CANT_DELETE_THE_FILE, e);
        }

    }


    @Override
    public InputStream recovery(String fileName) {
        try {
            Path newPath = getFilePath(fileName);
           return Files.newInputStream(newPath);
        }  catch (Exception e) {
            throw new StorageException(SYSTEM_CANT_RECOVERY_FILE_FROM_STORAGE, e);
        }
    }

    private Path getFilePath(String fileName) {
        //return photoDirectory.resolve(Path.of(fileName));
        return storageProperties.getLocal().getPhotosDirecotry().resolve(Path.of(fileName));
    }
}
