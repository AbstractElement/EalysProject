
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.ResetTokenDTO;
import com.sam_solutions.ealys.entity.ResetToken;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting ResetToken object to ResetTokenDTO object.
 */
public class ResetTokenConverter implements Converter<ResetToken, ResetTokenDTO> {

    /**
     * Converting ResetToken object to ResetTokenDTO object.
     * @param resetToken - ResetToken object
     */
    @Override
    public ResetTokenDTO convert(final ResetToken resetToken) {
        ResetTokenDTO resetTokenDTO = new ResetTokenDTO();
        resetTokenDTO.setTokenId(resetToken.getTokenId());
        resetTokenDTO.setToken(resetToken.getToken());
        resetTokenDTO.setEmail(resetToken.getEmail());
        resetTokenDTO.setDateActivate(resetToken.getDateActivate());
        resetTokenDTO.setDateCreate(resetToken.getDateCreate());
        resetTokenDTO.setEnabled(resetToken.getEnabled());
        resetTokenDTO.setUserIP(resetToken.getUserIP());
        resetTokenDTO.setUser(new UserConverter().convert(resetToken.getUser()));
        return resetTokenDTO;
    }
}
