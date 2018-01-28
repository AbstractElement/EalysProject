
package com.sam_solutions.ealys.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class for storing RoleOnProjectDTO object.
 */
@Data
public class RoleOnProjectDTO {
    /**
     * Id
     */
    private Long id;

    /**
     * User
     */
    @NotNull
    private UserDTO user;

    /**
     * User role on project
     */
    @NotNull
    private String roleOnProject;
}
