
package com.sam_solutions.ealys.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class for storing Role DTO object.
 */
@Data
public class RoleDTO {
    /**
     * Role ID
     */
    private Long roleId;

    /**
     * Role name
     */
    @NotNull
    private String role;

    /**
     * User
     */
    @NotNull
    private UserDTO user;
}
