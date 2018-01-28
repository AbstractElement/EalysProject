
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.ImageDTO;
import com.sam_solutions.ealys.entity.Image;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting ImageDTO class to Image class.
 */
public class ImageDTOConverter implements Converter<ImageDTO, Image> {

    /**
     * Converting ImageDTO object to Image object
     * @param imageDTO - ImageDTO object
     */
    @Override
    public Image convert(final ImageDTO imageDTO) {
        Image image = new Image();
        image.setImageId(imageDTO.getImageId());
        image.setImageName(imageDTO.getImageName());
        return image;
    }
}
