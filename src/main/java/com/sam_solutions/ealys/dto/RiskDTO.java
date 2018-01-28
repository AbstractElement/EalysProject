
package com.sam_solutions.ealys.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.FieldError;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing Risk DTO object.
 */
@Data
public class RiskDTO {
    /**
     * Risk ID
     */
    private Long riskId;

    /**
     * Author of risk
     */
    private UserDTO author;

    /**
     * Risk name
     */
    @NotEmpty
    @Size(max = 255)
    private String name;

    /**
     * Risk description
     */
    @Size(min = 10, max = 255)
    private String description;

    /**
     * Risk danger level
     */
    @Min(1)
    @Max(10)
    @NotNull
    private Integer dangerLevel;

    /**
     * Risk validation errors
     */
    private List<FieldError> errors = new ArrayList<>();
}
