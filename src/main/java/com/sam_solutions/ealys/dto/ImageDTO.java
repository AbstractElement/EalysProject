
package com.sam_solutions.ealys.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Class for storing image DTO object.
 */
@Data
public class ImageDTO{
    /**
     * Image id
     */
    private Long imageId;

    /**
     * Image name
     */
    @NotNull
    private String imageName;
}
