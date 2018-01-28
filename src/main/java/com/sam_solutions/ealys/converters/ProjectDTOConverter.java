
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.ImageDTO;
import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RiskDTO;
import com.sam_solutions.ealys.dto.RoleOnProjectDTO;
import com.sam_solutions.ealys.entity.Project;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting ProjectDTO class to Project class.
 */
public class ProjectDTOConverter implements Converter<ProjectDTO, Project> {

    /**
     * Converting ProjectDTO object to Project object.
     * @param projectDTO - ProjectDTO object
     */
    @Override
    public Project convert(final ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectId(projectDTO.getProjectId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setTarget(projectDTO.getTarget());
        project.setDate(projectDTO.getDate());
        for (ImageDTO imageDTO : projectDTO.getImages())
            project.getImages().add(new ImageDTOConverter().convert(imageDTO));
        for (RiskDTO riskDTO : projectDTO.getRisks())
            project.getRisks().add(new RiskDTOConverter().convert(riskDTO));
        for (RoleOnProjectDTO userDTO : projectDTO.getUsers())
            project.getUsers().add(new RoleOnProjectDTOConverter().convert(userDTO));
        return project;
    }
}
