
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.TransientUserDTO;

/**
 * Interface for working with registration process.
 */
public interface RegistrationService {
    /**
     * Checking registration form
     * @param userDTO - user object
     */
    TransientUserDTO checkRegistrationForm(TransientUserDTO userDTO);

    /**
     * Registering new user
     * @param userDTO - user object
     */
    void registeringNewUser(TransientUserDTO userDTO);
}
