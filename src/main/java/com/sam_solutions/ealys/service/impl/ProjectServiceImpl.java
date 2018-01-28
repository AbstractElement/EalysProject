
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dao.ProjectDAO;
import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RoleOnProjectDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.RoleOnProject;
import com.sam_solutions.ealys.service.ProjectService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with projects.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    /**
     * Class for working with projects table
     */
    private final ProjectDAO projectDAO;

    /**
     * For converting objects
     */
    private final ConversionService conversionService;

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(Project.class);

    @Autowired
    public ProjectServiceImpl(final ProjectDAO projectDAO, final ConversionService conversionService) {
        this.projectDAO = projectDAO;
        this.conversionService = conversionService;
    }

    /**
     * @see ProjectService#addProject(ProjectDTO)
     * @param project - project object
     */
    @Override
    @Transactional
    public void addProject(final ProjectDTO project){
        try {
            projectDAO.create(conversionService.convert(project, Project.class));
            LOGGER.info("Project was added, id: " + project.getProjectId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error adding project" + ex);
            throw ex;
        }
    }

    /**
     * @see ProjectService#getProjectById(Long)
     * @param id - project id
     * @return
     */
    @Override
    @Transactional
    public ProjectDTO getProjectById(final Long id){
        try {
            LOGGER.info("Retrieve project by id");
            return conversionService.convert(projectDAO.read(Project.class, id), ProjectDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error retrieve project by id", ex);
            throw ex;
        }
    }

    /**
     * @see ProjectService#deleteProject(ProjectDTO)
     * @param project - project object
     */
    @Override
    @Transactional
    public void deleteProject(final ProjectDTO project) {
        try {
            LOGGER.info("Delete project, id: " + project.getProjectId());
            projectDAO.delete(conversionService.convert(project, Project.class));
        }
        catch (JDBCException ex){
            LOGGER.error("Error deleting project", ex);
            throw ex;
        }
    }

    /**
     * @see ProjectService#updateProject(ProjectDTO)
     * @param projectDTO
     */
    @Override
    @Transactional
    public void updateProject(final ProjectDTO projectDTO) {
        try{
            Project project = conversionService.convert(projectDTO, Project.class);
            projectDAO.update(project);
            LOGGER.info("Update project, id: " + project.getProjectId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error updating project", ex);
            throw ex;
        }
    }

    /**
     * @see ProjectService#findLimitByUserId(Long, int)
     * @param userId - user id
     * @param start - postion of the fist result
     * @return
     */
    @Override
    @Transactional
    public List<ProjectDTO> findLimitByUserId(final Long userId, final int start) {
        try{
            LOGGER.info("Finding projects by user id: " + userId);
            List<Project> projects = projectDAO.findLimitByUserId(userId, start);
            List<ProjectDTO> projectDTOList = new ArrayList<>();
            for (Project project : projects)
                projectDTOList.add(conversionService.convert(project, ProjectDTO.class));
            return projectDTOList;
        }
        catch (JDBCException ex){
            LOGGER.error("Error finding projects by user id: " + userId, ex);
            throw ex;
        }
    }

    /**
     * @see ProjectService#getAmountProjects(Long)
     * @param userId - user id.
     * @return
     */
    @Override
    @Transactional
    public long getAmountProjects(final Long userId) {
        try{
            LOGGER.info("Getting amount user projects");
            return projectDAO.getAmountProjects(userId);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting amount user project", ex);
            throw ex;
        }
    }

    /**
     * @see ProjectService#findUserProject(Long, Long)
     * @param projectId - project id
     * @param userId - user id
     * @return
     */
    @Override
    @Transactional
    public RoleOnProjectDTO findUserProject(final Long projectId, final Long userId) {
        try{
            LOGGER.info("Finding user project by user id and project id");
            return conversionService.convert(projectDAO.findUserProject(projectId, userId), RoleOnProjectDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error finding user project", ex);
            throw ex;
        }
    }

    /**
     * @see ProjectService#checkUserAccessOnProject(Long, UserDTO)
     * @param projectId - project id
     * @param user
     * @return
     */
    @Override
    @Transactional
    public String checkUserAccessOnProject(final Long projectId, final UserDTO user) {
        try {
            RoleOnProject roleOnProject = projectDAO.findUserProject(projectId, user.getUserId());
            String access = "NONE";
            if (roleOnProject.getUser().getUsername().equals(user.getUsername()))
                access = roleOnProject.getRoleOnProject();
            LOGGER.info("Retrieve user role on project (Project id: " + projectId + " user name: " + user.getUsername() + " access: " + access + ")");
            return access;
        }
        catch (JDBCException ex){
            LOGGER.error("Error checking user access on project", ex);
            throw ex;
        }
    }
}
