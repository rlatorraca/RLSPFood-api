package ca.com.rlsp.rlspfoodapi.domain.model.mixin;

import ca.com.rlsp.rlspfoodapi.domain.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
    Faz c configuracao / conexao na Classe (CUISINE) em relacao as anotacoes do JACKSON (@Json*)
 */
public abstract class CuisineMixin {


    @JsonIgnore /// remove da Apresentacao da Classe de Dominio na resposta (no Json de resposta))
    private List<Restaurant> restaurants ;
}
