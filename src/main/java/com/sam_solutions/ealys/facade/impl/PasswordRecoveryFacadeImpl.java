
package com.sam_solutions.ealys.facade.impl;

import com.sam_solutions.ealys.dto.ResetTokenDTO;
import com.sam_solutions.ealys.dto.TransientUserDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.facade.PasswordRecoveryFacade;
import com.sam_solutions.ealys.service.EmailService;
import com.sam_solutions.ealys.service.ResetTokenService;
import com.sam_solutions.ealys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Class for working with reset password tokens.
 */
@Component
public class PasswordRecoveryFacadeImpl implements PasswordRecoveryFacade {

    /**
     * @see UserService
     */
    private final UserService userService;

    /**
     * @see ResetTokenService
     */
    private final ResetTokenService resetTokenService;

    /**
     * Object for encrypted password
     * @see PasswordEncoder
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * @see EmailService
     */
    private final EmailService emailService;

    @Autowired
    public PasswordRecoveryFacadeImpl(final UserService userService, final ResetTokenService resetTokenService,
                                      final PasswordEncoder passwordEncoder, final EmailService emailService) {
        this.userService = userService;
        this.resetTokenService = resetTokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    /**
     * @see PasswordRecoveryFacade#restorePass(String, String)
     */
    @Override
    public boolean restorePass(final String email, final String lang) {
        if (userService.containsEmail(email)){
            ResetTokenDTO resetToken = new ResetTokenDTO();
            resetToken.setToken(resetTokenService.generateToken());
            resetToken.setEmail(email);
            resetToken.setUser(userService.getUserByEmail(email));
            resetToken.setDateCreate(new Date());
            resetToken.setEnabled((byte)(1));
            resetTokenService.save(resetToken);
            emailService.sendResetPasswordEmail(resetToken, lang);
            return true;
        }
        else
            return false;
    }

    /**
     * @see PasswordRecoveryFacade#findByToken(String)
     */
    @Override
    public ResetTokenDTO findByToken(final String token) {
        return resetTokenService.getByToken(token);
    }

    /**
     * @see PasswordRecoveryFacade#activateToken(ResetTokenDTO, String)
     */
    @Override
    public TransientUserDTO activateToken(final ResetTokenDTO resetTokenDTO, final String userIP) {
        if (resetTokenDTO != null && resetTokenDTO.getEnabled() != 0) {
            resetTokenDTO.setDateActivate(new Date());
            resetTokenDTO.setUserIP(userIP);
            resetTokenDTO.setEnabled((byte)(0));
            resetTokenService.update(resetTokenDTO);
            TransientUserDTO userDTO = new TransientUserDTO();
            userDTO.setEmail(resetTokenDTO.getEmail());
            return userDTO;
        }
        else
            return null;
    }

    /**
     * @see PasswordRecoveryFacade#confirmNewPass(TransientUserDTO)
     */
    @Override
    public boolean confirmNewPass(final TransientUserDTO userDTO) {
        UserDTO user = userService.getUserByEmail(userDTO.getEmail());
        if (userDTO.getPassword().equals(userDTO.getRepeatedPassword())) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userService.updateUser(user);
            return true;
        }
        else return false;
    }
}
