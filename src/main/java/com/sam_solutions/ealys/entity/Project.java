
package com.sam_solutions.ealys.entity;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for storing Project object.
 */
@Data
public class Project {
    /**
     * Project ID
     */
    private Long projectId;

    /**
     * Project name
     */
    private String name;

    /**
     * Project description
     */
    private String description;

    /**
     * Project target
     */
    private String target;

    /**
     * Project images
     */
    private List<Image> images = new ArrayList<>();

    /**
     * Project risks
     */
    private List<Risk> risks = new ArrayList<>();

    /**
     * Project group
     */
    private List<RoleOnProject> users = new ArrayList<>();

    /**
     * Date of create project
     */
    @Temporal(TemporalType.DATE)
    private Date date;
}
