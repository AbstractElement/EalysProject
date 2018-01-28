
package com.sam_solutions.ealys.facade;

import com.itextpdf.text.DocumentException;
import com.sam_solutions.ealys.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * Interface for working with projects.
 */
public interface ProjectFacade {
    /**
     * Saving new project
     * @param projectDTO - project object
     */
    ProjectDTO saveProject(ProjectDTO projectDTO);

    /**
     * Updating project
     * @param projectDTO - project object
     */
    ProjectDTO updateProject(ProjectDTO projectDTO);

    /**
     * Retirieve project by id
     * @param id - project id
     */
    ProjectDTO findProjectById(Long id);

    /**
     * Leaving project
     * @param projectId - project id
     */
    void leaveProject(Long projectId);

    /**
     * Generating project PDF file.
     * @param projectId - project id
     * @param lang - current language.
     * @throws IOException
     * @throws DocumentException
     */
    ResponseEntity<byte[]> getProjectPDF(Long projectId, String lang) throws IOException, DocumentException;

    /**
     * Retrieves user projects from the interval
     * @param pageNumber - page number
     */
    List<ProjectDTO> findLimitProjects(int pageNumber);

    /**
     * Checking user access on the project
     * @param id - project id
     * @param username - username
     */
    String checkUserAccessOnProject(Long id, String username);
}
