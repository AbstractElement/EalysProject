
package com.sam_solutions.ealys.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for working woth images.
 */
public interface ImageService {
    /**
     * Uploading images
     * @param path - path to images folder
     * @param images - images array
     * @param uuid - uuids array for images
     */
    void uploadImages(String path, MultipartFile[] images, String[] uuid);

    /**
     * Generating UUID for image
     * @param size - amount images
     */
    String[] generateUUID(int size);

    /**
     * Removing image from folder.
     * @param path - path to folder with images.
     * @param image - image name.
     */
    void removeImage(String path, String image);
}
