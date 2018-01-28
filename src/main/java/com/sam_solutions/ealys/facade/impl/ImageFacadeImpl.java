
package com.sam_solutions.ealys.facade.impl;

import com.sam_solutions.ealys.dto.ImageDTO;
import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.facade.ImageFacade;
import com.sam_solutions.ealys.service.ImageService;
import com.sam_solutions.ealys.service.ProjectService;
import com.sam_solutions.ealys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

/**
 * Class for working with images.
 */
@Component
public class ImageFacadeImpl implements ImageFacade {

    /**
     * Information about server context
     */
    private final ServletContext servletContext;

    /**
     * Service for working with users object
     */
    private final UserService userService;

    /**
     * Service for working with images object
     */
    private final ImageService imageService;

    /**
     * Service for working with projects objects
     */
    private final ProjectService projectService;

    /**
     * Path to users pjotos (Property value: util.properties)
     */
    @Value("${path.users.photos}")
    private String pathToUsersPhotos;

    /**
     * Path to projects photos (Property value: util.properties)
     */
    @Value("${path.projects.photos}")
    private String pathToProjectsPhotos;

    @Autowired
    public ImageFacadeImpl(final ServletContext servletContext, final UserService userService,
                           final ImageService imageService, final ProjectService projectService) {
        this.servletContext = servletContext;
        this.userService = userService;
        this.imageService = imageService;
        this.projectService = projectService;
    }

    /**
     * @see ImageFacade#uploadUserPhoto(MultipartFile[])
     */
    @Override
    public void uploadUserPhoto(final MultipartFile[] images) {
        String path = servletContext.getRealPath("") + pathToUsersPhotos;
        UserDTO user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        String[] uuidArr = imageService.generateUUID(images.length);
        user.setPhoto(uuidArr[0]);
        userService.updateUser(user);
        imageService.uploadImages(path, images, uuidArr);
    }

    /**
     * @see ImageFacade#uploadProjectPhoto(MultipartFile[], Long)
     */
    @Override
    public void uploadProjectPhoto(final MultipartFile[] images, final Long projectId) {
        String path = servletContext.getRealPath("") + pathToProjectsPhotos;
        String[] uuidArr = imageService.generateUUID(images.length);
        for (int i = 0; i < images.length; i++) {
            ProjectDTO project = projectService.getProjectById(projectId);
            ImageDTO photo = new ImageDTO();
            photo.setImageName(uuidArr[i]);
            project.getImages().add(photo);
            projectService.updateProject(project);
        }
        imageService.uploadImages(path, images, uuidArr);
    }

    /**
     * @see ImageFacade#removeProjectPhoto(Long, ImageDTO)
     */
    @Override
    public void removeProjectPhoto(final Long projectId, final ImageDTO data) {
        ProjectDTO project = projectService.getProjectById(projectId);
        project.getImages().remove(data);
        projectService.updateProject(project);
        imageService.removeImage(servletContext.getRealPath("") + pathToProjectsPhotos, data.getImageName());
    }
}
