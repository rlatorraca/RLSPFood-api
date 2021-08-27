package ca.com.rlsp.rlspfoodapi.domain.exception;


public class ClientNotFoundException extends EntityNotFoundException {

    public static final String MSG_CLIENT_IS_NOT_FOUND_DATABASE = "Client as code is %d not found into the Database";

    public ClientNotFoundException(String msg){
        super(msg);
    }

    public ClientNotFoundException(Long clientId) {
        this(String.format(MSG_CLIENT_IS_NOT_FOUND_DATABASE, clientId));
    }
}