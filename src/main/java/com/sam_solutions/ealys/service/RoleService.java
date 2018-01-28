
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.RoleDTO;

/**
 * Interface for working with user role in application.
 */
public interface RoleService {
    /**
     * Set role for user
     * @param role - role object
     */
    void addUserRole(RoleDTO role);
}
