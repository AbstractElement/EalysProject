
package com.sam_solutions.ealys.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.FieldError;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for storing Project DTO object.
 */
@Data
public class ProjectDTO {
    /**
     * Project id
     */
    private Long projectId;

    /**
     * Project name
     */
    @Size(min = 4, max = 255)
    @NotEmpty
    private String name;

    /**
     * Project description
     */
    @Size(max = 10000)
    @NotEmpty
    private String description;

    /**
     * Project target
     */
    @Size(max = 400)
    @NotEmpty
    private String target;

    /**
     * Date of create
     */
    private Date date;

    /**
     * Project risks
     */
    private List<RiskDTO> risks = new ArrayList<>();

    /**
     * Project images
     */
    private List<ImageDTO> images = new ArrayList<>();

    /**
     * Project users
     */
    private List<RoleOnProjectDTO> users = new ArrayList<>();

    /**
     * Validation errors
     */
    private List<FieldError> errors = new ArrayList<>();


}
