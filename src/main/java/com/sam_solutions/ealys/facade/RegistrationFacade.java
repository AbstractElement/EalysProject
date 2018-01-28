
package com.sam_solutions.ealys.facade;

import com.sam_solutions.ealys.dto.TransientUserDTO;

import javax.mail.MessagingException;

/**
 * Interface for working with registration process.
 */
public interface RegistrationFacade {
    /**
     * Registration new user in application
     * @param userDTO - user object
     * @param lang - current language
     * @throws MessagingException - generation mail
     */
    TransientUserDTO registrationUser(TransientUserDTO userDTO, String lang) throws MessagingException;
}
