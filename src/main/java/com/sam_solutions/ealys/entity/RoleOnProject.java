
package com.sam_solutions.ealys.entity;

import lombok.Data;

/**
 * Class for storing RoleOnProject object.
 */
@Data
public class RoleOnProject {
    /**
     * ID
     */
    private Long id;

    /**
     * User
     */
    private User user;

    /**
     * Project
     */
    private Project project;

    /**
     * User role on project
     */
    private String roleOnProject;
}
