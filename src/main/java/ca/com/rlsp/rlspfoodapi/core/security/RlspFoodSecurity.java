package ca.com.rlsp.rlspfoodapi.core.security;

import ca.com.rlsp.rlspfoodapi.domain.repository.OrderRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;


@Component
public class RlspFoodSecurity {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Authentication getAuthentication() {
        // Pega o Contexto atual de seguranca e um objeto do Token que representa a autenticaca atual
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal(); // Retorna um instancia de JWT (do usuario autenticado)

        return jwt.getClaim("user_id");
    }

    public boolean manageRestaurant(Long restaurantId){
        return restaurantRepository.restaurantHasManager(restaurantId, getUserId());
    }

    public boolean manageRestaurantOrders(String orderCode) {
        return orderRepository.isOrderManagedFor(orderCode, getUserId());
    }

    public boolean userAuthenticatedAndEqualUserPassed(Long userId){
        return getUserId() != null && userId != null && getUserId() == userId;
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean hasPermissionToModifyOrderStatus(String orderCode){
        return hasAuthority("SCOPE_WRITE")
                && (
                hasAuthority("EDIT_ORDERS") || manageRestaurantOrders(orderCode));
    }

}
