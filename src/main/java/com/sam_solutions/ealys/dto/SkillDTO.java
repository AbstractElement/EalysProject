
package com.sam_solutions.ealys.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class for storing SkillDTO object.
 */
@Data
public class SkillDTO {
    /**
     * Skill ID
     */
    private Long skillId;

    /**
     * Skill name
     */
    @NotNull
    @Size(max = 255)
    private String name;

}
