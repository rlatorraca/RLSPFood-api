package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClientInputDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;



}
