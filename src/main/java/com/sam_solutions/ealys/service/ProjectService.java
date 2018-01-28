
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RoleOnProjectDTO;
import com.sam_solutions.ealys.dto.UserDTO;

import java.util.List;

/**
 * Interface for working with projects.
 */
public interface ProjectService {
    /**
     * Add new project in DB
     * @param project - project object
     */
    void addProject(ProjectDTO project);

    /**
     * Retrieve project by ID
     * @param id - project id
     */
    ProjectDTO getProjectById(Long id);

    /**
     * Deleteing project from DB
     * @param project - project object
     */
    void deleteProject(ProjectDTO project);

    /**
     * Updating project.
     * @param project - project object
     */
    void updateProject(ProjectDTO project);

    /**
     * Retrieve user projects from the interval
     * @param userId - user id
     * @param start - postion of the fist result
     */
    List<ProjectDTO> findLimitByUserId(Long userId, int start);

    /**
     * Retrieve amount user project.
     * @param userId - user id.
     */
    long getAmountProjects(Long userId);

    /**
     * Retrieve user role on project
     * @param projectId - project id
     * @param userId - user id
     */
    RoleOnProjectDTO findUserProject(Long projectId, Long userId);

    /**
     * Checking user access ob project
     * @param projectId - project id
     * @param userDTO - user object
     * @return - user role of NONE if user doesn't exist on project
     */
    String checkUserAccessOnProject(Long projectId, UserDTO userDTO);
}
