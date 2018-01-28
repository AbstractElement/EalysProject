
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.RoleDTO;
import com.sam_solutions.ealys.entity.Role;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting Role class to RoleDTO class.
 */
public class RoleConverter implements Converter<Role, RoleDTO> {

    /**
     * Converting Role object to RoleDTO object
     * @param role - Role object
     */
    @Override
    public RoleDTO convert(final Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(role.getRoleId());
        roleDTO.setRole(role.getRole());
        roleDTO.setUser(new UserConverter().convert(role.getUser()));
        return roleDTO;
    }

}
