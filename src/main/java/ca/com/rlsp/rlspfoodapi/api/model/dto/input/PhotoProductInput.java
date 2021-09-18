package ca.com.rlsp.rlspfoodapi.api.model.dto.input;


import ca.com.rlsp.rlspfoodapi.core.validation.FileContentType;
import ca.com.rlsp.rlspfoodapi.core.validation.PhotoSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PhotoProductInput {

    @NotNull
    @PhotoSize(max="500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE} )
    private MultipartFile file;
    @NotBlank
    private String description;
}
