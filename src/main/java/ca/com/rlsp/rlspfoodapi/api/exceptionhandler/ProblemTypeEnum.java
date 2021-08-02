package ca.com.rlsp.rlspfoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemTypeEnum {

    ENTITY_IN_USE("/entity-in-use", "Entity has been used by other entity"),
    BUSINESS_RULES_HAS_ERROR("/business-has-error", "Business rules was violated"),
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found");

    private String uri;
    private String title;

    ProblemTypeEnum(String path, String title) {
        this.uri = "https://www.rlspfood.ca"+ path;
        this.title = title;
    }
}
