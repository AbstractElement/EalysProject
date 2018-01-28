
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.ImageDTO;
import com.sam_solutions.ealys.entity.Image;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting Image class to ImageDTO class.
 */
public class ImageConverter implements Converter<Image, ImageDTO> {

    /**
     * Converting Image object to ImageDTO object
     * @param image - Image object
     */
    @Override
    public ImageDTO convert(final Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImageId(image.getImageId());
        imageDTO.setImageName(image.getImageName());
        return imageDTO;
    }
}
