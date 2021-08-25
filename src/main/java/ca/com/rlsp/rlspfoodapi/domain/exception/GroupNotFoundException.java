package ca.com.rlsp.rlspfoodapi.domain.exception;

import org.springframework.http.HttpStatus;

public class GroupNotFoundException extends EntityNotFoundException{


    public GroupNotFoundException(Long groupId) {
        this(String.format("Group by code %d doesn existe on database", groupId));
    }

    public GroupNotFoundException(String msg) {
        super(msg);
    }
}
