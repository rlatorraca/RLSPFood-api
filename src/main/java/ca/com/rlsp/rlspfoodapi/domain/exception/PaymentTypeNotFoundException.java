package ca.com.rlsp.rlspfoodapi.domain.exception;

public class PaymentTypeNotFoundException extends  EntityNotFoundException{

    public PaymentTypeNotFoundException(String msg) {
        super(msg);
    }

    public PaymentTypeNotFoundException(Long paymentTypeId) {
        this(String.format("Payment Type by code %s doesn't exist",paymentTypeId));
    }
}
