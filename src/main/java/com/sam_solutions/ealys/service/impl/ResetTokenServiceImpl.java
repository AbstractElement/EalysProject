
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dao.ResetTokenDAO;
import com.sam_solutions.ealys.dto.ResetTokenDTO;
import com.sam_solutions.ealys.entity.ResetToken;
import com.sam_solutions.ealys.service.ResetTokenService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Class for working with reset password tokens.
 */
@Service
public class ResetTokenServiceImpl implements ResetTokenService {

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(ResetToken.class);

    /**
     * For converted objects
     */
    private final ConversionService conversionService;

    /**
     * For working witr tokens table DB
     */
    private final ResetTokenDAO resetTokenDAO;

    @Autowired
    public ResetTokenServiceImpl(final ConversionService conversionService, final ResetTokenDAO resetTokenDAO) {
        this.conversionService = conversionService;
        this.resetTokenDAO = resetTokenDAO;
    }

    /**
     * @see ResetTokenService#save(ResetTokenDTO)
     * @param resetToken - reset token object
     */
    @Override
    @Transactional
    public void save(final ResetTokenDTO resetToken) {
        try {
            resetTokenDAO.create(conversionService.convert(resetToken, ResetToken.class));
            LOGGER.info("Token was saved, id: " + resetToken.getTokenId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error saving token", ex);
            throw ex;
        }
    }

    /**
     * @see ResetTokenService#update(ResetTokenDTO)
     * @param resetToken - token object
     */
    @Override
    @Transactional
    public void update(final ResetTokenDTO resetToken) {
        try {
            resetTokenDAO.update(conversionService.convert(resetToken, ResetToken.class));
            LOGGER.info("Token was updated, id: " + resetToken.getTokenId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error updating token", ex);
            throw ex;
        }
    }

    /**
     * @see ResetTokenService#getByToken(String)
     * @param token - token
     */
    @Override
    @Transactional
    public ResetTokenDTO getByToken(final String token) {
        try {
            LOGGER.info("Finding token by token name, token: " + token);
            return conversionService.convert(resetTokenDAO.getByToken(token), ResetTokenDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting token", ex);
            throw ex;
        }
    }

    /**
     * @see ResetTokenService#generateToken()
     */
    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
