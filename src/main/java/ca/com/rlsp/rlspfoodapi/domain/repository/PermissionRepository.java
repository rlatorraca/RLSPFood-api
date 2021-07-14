package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> listAll();
    Permission findById(Long id);
    Permission save(Permission permission);
    void remove(Permission permission);
}
