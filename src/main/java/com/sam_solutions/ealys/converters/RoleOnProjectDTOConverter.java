
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.RoleOnProjectDTO;
import com.sam_solutions.ealys.entity.RoleOnProject;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting RoleOnProjectDTO class to RoleOnProject class.
 */
public class RoleOnProjectDTOConverter implements Converter<RoleOnProjectDTO, RoleOnProject> {

    /**
     * Converting RoleOnProjectDTO object to RoleOnProject object
     * @param roleOnProjectDTO - RoleOnProjectDTO object
     */
    @Override
    public RoleOnProject convert(final RoleOnProjectDTO roleOnProjectDTO) {
        RoleOnProject roleOnProject = new RoleOnProject();
        roleOnProject.setId(roleOnProjectDTO.getId());
        roleOnProject.setRoleOnProject(roleOnProjectDTO.getRoleOnProject());
        roleOnProject.setUser(new UserDTOConverter().convert(roleOnProjectDTO.getUser()));
        return roleOnProject;
    }
}
