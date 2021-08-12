package ca.com.rlsp.rlspfoodapi.core.jackson;

import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.model.mixin.RestaurantMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

/*
    Registramos os modulos do JACKSON que estaremos usando na aplicacao
     - Registrando serializador
     - Desregistrando desserializador
 */
@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule(){
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
    }

}
