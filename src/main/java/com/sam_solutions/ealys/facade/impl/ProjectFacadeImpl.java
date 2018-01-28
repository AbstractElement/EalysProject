
package com.sam_solutions.ealys.facade.impl;

import com.itextpdf.text.DocumentException;
import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RoleOnProjectDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.facade.ProjectFacade;
import com.sam_solutions.ealys.service.PDFGeneratorService;
import com.sam_solutions.ealys.service.ProjectService;
import com.sam_solutions.ealys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Class for working with projects.
 */
@Component
public class ProjectFacadeImpl implements ProjectFacade{
    /**
     * @see UserService
     */
    private final UserService userService;

    /**
     * @see ProjectService
     */
    private final ProjectService projectService;

    /**
     * @see PDFGeneratorService
     */
    private final PDFGeneratorService pdfGeneratorService;

    /**
     * User role on project (Property value)
     */
    @Value("${role.owner}")
    private String roleOwner;

    /**
     * Amoutn projects on page (Property value)
     */
    @Value("${projects.on.page}")
    private int amountProjectsOnPage;

    @Autowired
    public ProjectFacadeImpl(final UserService userService, final ProjectService projectService,
                             final PDFGeneratorService pdfGeneratorService) {
        this.userService = userService;
        this.projectService = projectService;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    /**
     * @see ProjectFacade#saveProject(ProjectDTO)
     * @param newProject - project object
     */
    @Override
    public ProjectDTO saveProject(final ProjectDTO newProject) {
        newProject.setDate(new Date());
        RoleOnProjectDTO roleOnProject = new RoleOnProjectDTO();
        roleOnProject.setUser(userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        roleOnProject.setRoleOnProject(roleOwner);
        newProject.getUsers().add(roleOnProject);
        projectService.updateProject(newProject);
        return newProject;
    }

    /**
     * @see ProjectFacade#updateProject(ProjectDTO)
     * @param updateProject - updated project
     */
    @Override
    public ProjectDTO updateProject(final ProjectDTO updateProject) {
        ProjectDTO project = projectService.getProjectById(updateProject.getProjectId());
        updateProject.setImages(project.getImages());
        updateProject.setUsers(project.getUsers());
        projectService.updateProject(updateProject);
        return updateProject;
    }

    /**
     * @see ProjectFacade#findProjectById(Long)
     * @param id - project id
     */
    @Override
    public ProjectDTO findProjectById(final Long id) {
        return projectService.getProjectById(id);
    }

    /**
     * @see ProjectFacade#leaveProject(Long)
     * @param projectId - project id
     */
    @Override
    public void leaveProject(final Long projectId) {
        UserDTO user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        ProjectDTO project = projectService.getProjectById(projectId);
        RoleOnProjectDTO roleOnProject = projectService.findUserProject(project.getProjectId(), user.getUserId());
        project.getUsers().remove(roleOnProject);
        projectService.updateProject(project);
        if (project.getUsers().size() == 0)
            projectService.deleteProject(project);
    }

    /**
     * @see ProjectFacade#getProjectPDF(Long, String)
     * @param projectId - project id
     * @param lang - current language.
     */
    @Override
    public ResponseEntity<byte[]> getProjectPDF(final Long projectId, final String lang) throws IOException, DocumentException {
        String pathPdf = pdfGeneratorService.getProjectPDF(projectId, lang);

        File file = new File(pathPdf);
        byte[] contents = new byte[(int) file.length()];
        new FileInputStream(file).read(contents);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "ProjectHAZOPTable.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
    }

    /**
     * @see ProjectFacade#findLimitProjects(int)
     * @param pageNumber - page number
     */
    @Override
    public List<ProjectDTO> findLimitProjects(final int pageNumber) {
        UserDTO user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        int start = pageNumber * amountProjectsOnPage - amountProjectsOnPage;
        return projectService.findLimitByUserId(user.getUserId(), start);
    }

    /**
     * @see ProjectFacade#checkUserAccessOnProject(Long, String)
     * @param id - project id
     * @param username - username
     */
    @Override
    public String checkUserAccessOnProject(final Long id, final String username) {
        return projectService.checkUserAccessOnProject(id, userService.getCurrentUser());
    }
}
