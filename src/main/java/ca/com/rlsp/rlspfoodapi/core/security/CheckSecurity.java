package ca.com.rlsp.rlspfoodapi.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
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

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_RESTAURANTS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToEdit {}

        @PreAuthorize("hasAuthority('SCOPE_READ') and  isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToQuery {}

        @PreAuthorize("(hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_RESTAURANTS')) or" +
                "@rlspFoodSecurity.manageRestaurant(#restaurantId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToManageRestaurant {}
    }

    public @interface Order {

        @PreAuthorize("hasAuthority('SCOPE_READ') and  isAuthenticated()")
        @PostAuthorize("hasAuthority('QUERY_ORDERS') or" +
                "@rlspFoodSecurity.getUserId() == returnObject.user.id or" +
                "@rlspFoodSecurity.manageRestaurant(returnObject.restaurant.id)" )
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToGetOneOrder {}

        @PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_PEDIDOS') or "
                + "@rlspFoodSecurity.getUserId() == #orderFilter.userId or"
                + "@rlspFoodSecurity.manageRestaurant(#orderFilter.restaurantId))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface hasPermissionToSearch { }
    }
}
