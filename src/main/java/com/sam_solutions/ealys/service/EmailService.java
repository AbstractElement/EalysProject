
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.InviteDTO;
import com.sam_solutions.ealys.dto.ResetTokenDTO;
import com.sam_solutions.ealys.dto.TransientUserDTO;

/**
 * Class for working with email.
 */
public interface EmailService {
    String FROM = "from";
    String TO = "to";
    String SUBJECT = "subject";
    String BCC_LIST = "bccList";
    String CCC_LIST = "ccList";

    /**
     * Sending email with infotmation about registration on project.
     * @param user - registered user
     * @param lang - current user
     */
    void sendRegistrationEmail(TransientUserDTO user, String lang);

    /**
     * Sending email with instruction how join in the project.
     * @param invite - invite information
     * @param lang - current language
     */
    void sendInviteEmail(InviteDTO invite, String lang);

    /**
     * Sending email with instruction how reset password.
     * @param resetTokenDTO - information about token
     * @param lang - current language
     */
    void sendResetPasswordEmail(ResetTokenDTO resetTokenDTO, String lang);
}
