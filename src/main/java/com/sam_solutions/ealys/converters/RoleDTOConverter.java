
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.RoleDTO;
import com.sam_solutions.ealys.entity.Role;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting RoleDTO class to Role class.
 */
public class RoleDTOConverter implements Converter<RoleDTO, Role> {

    /**
     * Converting RoleDTO object to Role object.
     * @param roleDTO - RoleDTO object.
     */
    @Override
    public Role convert(final RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRole(roleDTO.getRole());
        role.setUser(new UserDTOConverter().convert(roleDTO.getUser()));
        return role;
    }
}
