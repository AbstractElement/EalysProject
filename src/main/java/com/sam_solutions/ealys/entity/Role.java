
package com.sam_solutions.ealys.entity;

import lombok.Data;

/**
 * Class for storing Role object.
 */
@Data
public class Role {
    /**
     * Role Id
     */
    private Long roleId;

    /**
     * Role name
     */
    private String role;

    /**
     * User
     */
    private User user;
}
