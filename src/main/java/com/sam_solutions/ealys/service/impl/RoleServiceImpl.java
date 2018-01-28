
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dao.GenericDAO;
import com.sam_solutions.ealys.dao.RoleDAO;
import com.sam_solutions.ealys.dto.RoleDTO;
import com.sam_solutions.ealys.entity.Role;
import com.sam_solutions.ealys.service.RoleService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for working with user role in application.
 */
@Service
public class RoleServiceImpl implements RoleService {
    /**
     * @see GenericDAO
     */
    private final RoleDAO roleDAO;

    /**
     * For converting objects
     */
    private final ConversionService conversionService;

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(Role.class);

    @Autowired
    public RoleServiceImpl(final RoleDAO roleDAO, final ConversionService conversionService) {
        this.roleDAO = roleDAO;
        this.conversionService = conversionService;
    }

    /**
     * @see RoleService#addUserRole(RoleDTO)
     * @param role - role object
     */
    @Override
    @Transactional
    public void addUserRole(final RoleDTO role) {
        try{
            roleDAO.create(conversionService.convert(role, Role.class));
            LOGGER.info("Add new user role in application, id: " + role.getRoleId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error adding user role in application", ex);
            throw ex;
        }

    }
}
