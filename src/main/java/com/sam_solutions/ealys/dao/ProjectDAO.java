
package com.sam_solutions.ealys.dao;

import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.RoleOnProject;

import java.util.List;

/**
 * Interface for working with user project table in DB.
 */
public interface ProjectDAO extends GenericDAO<Project, Long>{

    /**
     * Retrieves user projects from the interval (start, stop) by user id.
     * @param userId - user id.
     * @param start - start interval.
     */
    List<Project> findLimitByUserId(Long userId, int start);

    /**
     * Retrieves amount user projects.
     * @param userId - user id
     */
    long getAmountProjects(Long userId);

    /**
     * Retrieves user, user role on project, project by project id and user id.
     * @param projectId - project id.
     * @param userId - user id.
     */
    RoleOnProject findUserProject(Long projectId, Long userId);

}
