
package com.sam_solutions.ealys.controllers;

import com.sam_solutions.ealys.facade.ImageFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for working with images.
 */
@Controller
@RequestMapping(value = "/photo")
public class ImageController{
    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(ImageController.class);
    /**
     * Class for working with images
     */
    private final ImageFacade imageFacade;

    @Autowired
    public ImageController(final ImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }

    /**
     * Function of uploading images of the server.
     * @param images - images that the user selected.
     * @param name - can be of two types: project or user.
     *             A label that helps you determine which folder to upload pictures to.
     * @param id - project id.
     * @param modelMap - for display exception on the page.
     * @throws NoSuchFieldException - before upload images.
     * @return - return project or user pages.
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPhoto(@RequestParam("images") final MultipartFile[] images,
                              @RequestParam("target") final String name,
                              @RequestParam(value = "projectId", required = false) final Long id,
                              final ModelMap modelMap) throws NoSuchFieldException{
        try {
            if (images != null) {
                if (name.equals("user"))
                    imageFacade.uploadUserPhoto(images);
                else if (name.equals("project")) {
                    imageFacade.uploadProjectPhoto(images, id);
                    return "redirect:/project/" + id;
                }
            }
            return "redirect:/user/0";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "redirect:/user/0";
        }
    }
}
