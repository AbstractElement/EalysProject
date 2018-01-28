
package com.sam_solutions.ealys.dao.impl;

import com.sam_solutions.ealys.dao.RoleDAO;
import com.sam_solutions.ealys.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * Class for working with user role in application.
 */
@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {
}
