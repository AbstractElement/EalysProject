
package com.sam_solutions.ealys.dao;

import com.sam_solutions.ealys.entity.ResetToken;

/**
 * Interface for working with table of reset password tokens.
 */
public interface ResetTokenDAO extends GenericDAO<ResetToken, Long> {
    /**
     * Return object with information about reset token
     * @param token - token
     */
    ResetToken getByToken(String token);
}
