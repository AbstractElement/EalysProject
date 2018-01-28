
package com.sam_solutions.ealys.facade;

import com.sam_solutions.ealys.dto.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for working with images.
 */
public interface ImageFacade {
    /**
     * Uploading user image
     * @param images - images array
     */
    void uploadUserPhoto(MultipartFile[] images);

    /**
     * Uploading project images.
     * @param images - images array
     * @param projectId - project id
     */
    void uploadProjectPhoto(MultipartFile[] images, Long projectId);

    /**
     * Remove project photo
     * @param projectId - project id
     * @param imageDTO - image object
     */
    void removeProjectPhoto(Long projectId, ImageDTO imageDTO);
}
