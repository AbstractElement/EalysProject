
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.entity.Image;
import com.sam_solutions.ealys.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Class for working with images.
 */
@Service
public class ImageServiceImpl implements ImageService {

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(Image.class);

    /**
     * @see ImageService#uploadImages(String, MultipartFile[], String[])
     */
    @Override
    public void uploadImages(final String path, final MultipartFile[] images, final String[] uuid) {
        for (int i = 0; i < images.length; i++) {
            try {
                File file = new File(path + "/" + uuid[i]);
                FileUtils.writeByteArrayToFile(file, images[i].getBytes());
                LOGGER.info("Successfully uploaded photo: " + images[i].getOriginalFilename());
            } catch (IOException e) {
                LOGGER.error("Failed to upload photo: " + e);
            }
        }
    }

    /**
     * @see ImageService#generateUUID(int)
     */
    @Override
    public String[] generateUUID(final int size) {
        String uuidArr[] = new String[size];
        for (int i = 0; i < size; i++)
            uuidArr[i] = UUID.randomUUID().toString() +  ".jpg";
        return uuidArr;
    }

    /**
     * @see ImageService#removeImage(String, String)
     */
    @Override
    public void removeImage(final String path, final String image) {
        File imgFile = new File(path + "/" + image);
        if (imgFile.delete()) {
            LOGGER.info("Image " + image + " was deleted");
        } else {
            LOGGER.error("Error deleting image " + image);
        }
    }
}
