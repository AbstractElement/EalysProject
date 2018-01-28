
package com.sam_solutions.ealys.facade.impl;

import com.sam_solutions.ealys.dto.TransientUserDTO;
import com.sam_solutions.ealys.facade.RegistrationFacade;
import com.sam_solutions.ealys.service.EmailService;
import com.sam_solutions.ealys.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

/**
 * Class for working with registration process.
 */
@Component
public class RegistrationFacadeImpl implements RegistrationFacade {

    /**
     * @see RegistrationService
     */
    private final RegistrationService registrationService;

    /**
     * @see EmailService
     */
    private final EmailService emailService;

    @Autowired
    public RegistrationFacadeImpl(final RegistrationService registrationService, final EmailService emailService) {
        this.registrationService = registrationService;
        this.emailService = emailService;
    }

    /**
     * @see RegistrationFacade#registrationUser(TransientUserDTO, String)
     * @param registrationForm - user data
     * @param lang - current language
     * @throws MessagingException - generation mail
     */
    @Override
    public TransientUserDTO registrationUser(final TransientUserDTO registrationForm, final String lang) throws MessagingException {
        TransientUserDTO form = registrationService.checkRegistrationForm(registrationForm);
        if (form.getErrors().size() == 0) {
            registrationService.registeringNewUser(form);
            emailService.sendRegistrationEmail(form, lang);
        }
        return form;
    }

}
