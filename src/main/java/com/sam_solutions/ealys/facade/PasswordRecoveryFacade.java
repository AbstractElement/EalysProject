
package com.sam_solutions.ealys.facade;

import com.sam_solutions.ealys.dto.ResetTokenDTO;
import com.sam_solutions.ealys.dto.TransientUserDTO;

/**
 * Interface for working with reset password tokens.
 */
public interface PasswordRecoveryFacade {
    /**
     * Confirm new password.
     * @param userDTO - object with new password and repeated new password
     * @return - false, if new password and repeated password not equals.
     */
    boolean confirmNewPass(TransientUserDTO userDTO);

    /**
     * Activate reset password token.
     * @param resetTokenDTO - object with information about token
     * @param userIP - user IP where activated token
     */
    TransientUserDTO activateToken(ResetTokenDTO resetTokenDTO, String userIP);

    /**
     * Prepare data for email message.
     * @param email - user email
     * @param lang - current language
     */
    boolean restorePass(String email, String lang);

    /**
     * Retrieves object by token
     * @param token - token
     */
    ResetTokenDTO findByToken(String token);
}
