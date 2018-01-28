
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.ResetTokenDTO;

/**
 * Interface for working with reset password token.
 */
public interface ResetTokenService {
    /**
     * Adding new token
     * @param resetToken - reset token object
     */
    void save(ResetTokenDTO resetToken);

    /**
     * Updating data of token
     * @param resetToken - token object
     */
    void update(ResetTokenDTO resetToken);

    /**
     * Retrieve token information by token
     * @param token - token
     */
    ResetTokenDTO getByToken(String token);

    /**
     * Generating token
     */
    String generateToken();
}
