
package com.sam_solutions.ealys.entity;

import lombok.Data;

/**
 * Class for storing Risk object.
 */
@Data
public class Risk {
    /**
     * Risk ID
     */
    private Long riskId;

    /**
     * Author of risk
     */
    private User author;

    /**
     * Risk name
     */
    private String name;

    /**
     * Risk description
     */
    private String description;

    /**
     * Risk danger level
     */
    private Integer dangerLevel;

    /**
     * Project where consist risk
     */
    private Project project;
}
