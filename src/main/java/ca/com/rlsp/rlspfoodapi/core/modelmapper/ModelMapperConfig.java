package ca.com.rlsp.rlspfoodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /*
        Como o Model Mapper nao eh controlado pelo SpringBoot temos que fazer ess asconfiguracao para anexa-lo dentro do SpringBoot
     */
    @Bean
    public ModelMapper modelMapper(){
        /*
         * Customizing moddelMapper to mapping a customized atttribute
         *
         *   var modelMapper = new ModelMapper();
		 *
		 *   modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
         * 	        .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
         *
         * return modelMapper;
         */


        return new ModelMapper();
    }
}
