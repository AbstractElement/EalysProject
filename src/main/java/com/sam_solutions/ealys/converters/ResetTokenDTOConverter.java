
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.ResetTokenDTO;
import com.sam_solutions.ealys.entity.ResetToken;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting ResetTokenDTO object to ResetToken.
 */
public class ResetTokenDTOConverter implements Converter<ResetTokenDTO, ResetToken> {

    /**
     * Converting ResetTokenDTO object to ResetToken object.
     * @param resetTokenDTO - ResetTokenDTO object.
     */
    @Override
    public ResetToken convert(final ResetTokenDTO resetTokenDTO) {
        ResetToken resetToken = new ResetToken();
        resetToken.setTokenId(resetTokenDTO.getTokenId());
        resetToken.setToken(resetTokenDTO.getToken());
        resetToken.setEmail(resetTokenDTO.getEmail());
        resetToken.setDateActivate(resetTokenDTO.getDateActivate());
        resetToken.setDateCreate(resetTokenDTO.getDateCreate());
        resetToken.setEnabled(resetTokenDTO.getEnabled());
        resetToken.setUserIP(resetTokenDTO.getUserIP());
        resetToken.setUser(new UserDTOConverter().convert(resetTokenDTO.getUser()));
        return resetToken;
    }
}
