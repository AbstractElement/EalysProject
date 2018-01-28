
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.RoleOnProjectDTO;
import com.sam_solutions.ealys.entity.RoleOnProject;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting RoleOnProject class to RoleOnProjectDTO class.
 */
public class RoleOnProjectConverter implements Converter<RoleOnProject, RoleOnProjectDTO> {

    /**
     * Converting RoleOnProject object to RoleOnProjectDTO object.
     * @param roleOnProject - RoleOnProject object
     */
    @Override
    public RoleOnProjectDTO convert(final RoleOnProject roleOnProject) {
        RoleOnProjectDTO roleOnProjectDTO = new RoleOnProjectDTO();
        roleOnProjectDTO.setId(roleOnProject.getId());
        roleOnProjectDTO.setRoleOnProject(roleOnProject.getRoleOnProject());
        roleOnProjectDTO.setUser(new UserConverter().convert(roleOnProject.getUser()));
        return roleOnProjectDTO;
    }

}
