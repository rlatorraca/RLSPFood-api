package ca.com.rlsp.rlspfoodapi.core.validation;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { MultipleDeliverFeeValidator.class})
public @interface MulitpleDeliveryFeeAnnotation {

     /*
        O tres atrinbutos abaixo sao obrigatorios
     */


    String message() default "{DeliveryFee.multiple}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int number();
}
