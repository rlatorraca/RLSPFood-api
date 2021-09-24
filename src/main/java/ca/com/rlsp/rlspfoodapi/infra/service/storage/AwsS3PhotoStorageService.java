package ca.com.rlsp.rlspfoodapi.infra.service.storage;

import ca.com.rlsp.rlspfoodapi.domain.service.PhotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AwsS3PhotoStorageService implements PhotoStorageService {
    @Autowired
    private AmazonS3 amazonS3; // Interface from AWS S3 API
    @Override
    public void storage(NewPhoto newPhoto) {

    }

    @Override
    public void remove(String fileName) {

    }

    @Override
    public InputStream recovery(String fileName) {
        return null;
    }

    @Override
    public String generateUUIDFileName(String orignalName, Long restaurantId, Long productId) {
        return PhotoStorageService.super.generateUUIDFileName(orignalName, restaurantId, productId);
    }

    @Override
    public void switchOrSave(String oldFileName, NewPhoto newPhoto) {
        PhotoStorageService.super.switchOrSave(oldFileName, newPhoto);
    }
}
