package ca.com.rlsp.rlspfoodapi.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    public @interface Cuisine {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_CUISINE')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToEdit {}

        @PreAuthorize("hasAuthority('SCOPE_READ') and  isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToQuery {}
    }

    public @interface Restaurant {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_RESTAURANT')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToEdit {}

        @PreAuthorize("hasAuthority('SCOPE_READ') and  isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToQuery {}
    }
}
