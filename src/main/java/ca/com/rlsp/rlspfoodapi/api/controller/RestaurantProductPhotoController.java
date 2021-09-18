package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PhotoProductInput;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping
    public void updatePhoto(@PathVariable Long restaurantId,
                            @PathVariable Long productId,
                            @Valid PhotoProductInput photoProductInput)  {

        String fileName = UUID.randomUUID().toString() + "_" + photoProductInput.getFile().getOriginalFilename();

        Path photoFile = Path.of("src/main/resources/photos/saved/",fileName);

        try {
            photoProductInput.getFile().transferTo(photoFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


