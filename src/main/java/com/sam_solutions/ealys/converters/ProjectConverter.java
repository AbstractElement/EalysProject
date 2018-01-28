
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.entity.Image;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.Risk;
import com.sam_solutions.ealys.entity.RoleOnProject;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting Project class to ProjectDTO class.
 */
public class ProjectConverter implements Converter<Project, ProjectDTO>{

    /**
     * Converting Project object to ProjectDTO object.
     * @param project - Project object.
     */
    @Override
    public ProjectDTO convert(final Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setTarget(project.getTarget());
        projectDTO.setDate(project.getDate());
        for (Image image : project.getImages())
            projectDTO.getImages().add(new ImageConverter().convert(image));
        for (Risk risk : project.getRisks())
            projectDTO.getRisks().add(new RiskConverter().convert(risk));
        for (RoleOnProject user : project.getUsers())
            projectDTO.getUsers().add(new RoleOnProjectConverter().convert(user));
        return projectDTO;
    }

}
