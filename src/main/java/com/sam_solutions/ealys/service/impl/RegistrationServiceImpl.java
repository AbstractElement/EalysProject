
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dto.RoleDTO;
import com.sam_solutions.ealys.dto.TransientUserDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.service.RegistrationService;
import com.sam_solutions.ealys.service.RoleService;
import com.sam_solutions.ealys.service.UserService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

/**
 * Class for working with registration process.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(RegistrationService.class);

    /**
     * @see UserService
     */
    private final UserService userService;

    /**
     * @see RoleService
     */
    private final RoleService roleService;

    /**
     * For encoging user password
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * For converting objects
     */
    private final ConversionService conversionService;

    @Autowired
    public RegistrationServiceImpl(final UserService userService, final RoleService roleService,
                                   final PasswordEncoder passwordEncoder, final ConversionService conversionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.conversionService = conversionService;
    }

    /**
     * @see RegistrationService#checkRegistrationForm(TransientUserDTO)
     * @param userDTO - user object
     */
    @Override
    @Transactional
    public TransientUserDTO checkRegistrationForm(final TransientUserDTO userDTO){
        try{
            if (userService.containsEmail(userDTO.getEmail())) {
                userDTO.getErrors().add(new FieldError("userDTO", "email", "Email address is exist!"));
                return userDTO;
            }
            if (userService.containsUsername(userDTO.getUsername())) {
                userDTO.getErrors().add(new FieldError("userDTO", "username", "Login is exist!"));
                return userDTO;
            }
            if (!userDTO.getPassword().equals(userDTO.getRepeatedPassword())) {
                userDTO.getErrors().add(new FieldError("userDTO", "repeatedPassword", "Passwords not equals!"));
                return userDTO;
            }
            LOGGER.info("Registration form was checked");
            return userDTO;
        }
        catch (JDBCException ex){
            LOGGER.error("Error in function checkRegistrationForm!", ex);
            throw ex;
        }
    }

    /**
     * @see RegistrationService#registeringNewUser(TransientUserDTO)
     * @param userDTO - user object
     */
    @Override
    @Transactional
    public void registeringNewUser(final TransientUserDTO userDTO){
        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            switch (userDTO.getSex()) {
                case "male":
                    userDTO.setPhoto("male.png");
                    break;
                case "female":
                    userDTO.setPhoto("female.png");
                    break;
                default:
                    userDTO.setPhoto("other.png");
                    break;
            }
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRole("ROLE_USER");
            roleDTO.setUser(conversionService.convert(userDTO, UserDTO.class));
            roleService.addUserRole(roleDTO);
            LOGGER.info("New user was added");
        }
        catch (JDBCException ex){
            LOGGER.error("Error registering new user!", ex);
            throw ex;
        }
    }

}
