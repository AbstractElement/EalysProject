
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dto.InviteDTO;
import com.sam_solutions.ealys.dto.ResetTokenDTO;
import com.sam_solutions.ealys.dto.TransientUserDTO;
import com.sam_solutions.ealys.service.EmailService;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import java.util.*;

/**
 * Class for working with email messages.
 */
@Service
public class EmailServiceImpl implements EmailService{
    /**
     * Name russian invite mail (Property value: util.properties)
     */
    @Value("${mail.invite.ru}")
    private String mailInviteRu;

    /**
     * Name english invite mail (Property value: util.properties)
     */
    @Value("${mail.invite.en}")
    private String mailInviteEn;

    /**
     * Name russian registration mail (Property value: util.properties)
     */
    @Value("${mail.registered.ru}")
    private String mailRegisteredRu;

    /**
     * Name english registration mail (Property value: util.properties)
     */
    @Value("${mail.registered.en}")
    private String mailRegisteredEn;

    /**
     * Name russian recovery password mail (Property value: util.properties)
     */
    @Value("${mail.recoveryPassword.ru}")
    private String mailRecoveryPasswordRu;

    /**
     * Name english recovery password mail (Property value: util.properties)
     */
    @Value("${mail.recoveryPassword.en}")
    private String mailRecoveryPasswordEn;

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(EmailService.class);

    /**
     * For creation JavaMail
     */
    private final JavaMailSender mailSender;

    /**
     * For generation mail
     */
    private final VelocityEngine velocityEngine;

    @Autowired
    public EmailServiceImpl(final JavaMailSender mailSender, final VelocityEngine velocityEngine) {
        this.mailSender = mailSender;
        this.velocityEngine = velocityEngine;
    }

    /**
     * @see EmailService#sendRegistrationEmail(TransientUserDTO, String)
     */
    @Override
    public void sendRegistrationEmail(final TransientUserDTO user, final String lang) {
        try {
            Map<String, Object> model = enterRegistrationEmailData(user);
            String template = "";
            if (lang.equals("en"))
                template = mailRegisteredEn;
            else if (lang.equals("ru"))
                template = mailRegisteredRu;
            MimeMessagePreparator preparator = getMimeMessagePreparator(template, model);
            mailSender.send(preparator);
            LOGGER.info("Registration email was sent");
        }
        catch (Exception ex){
            LOGGER.error("Error sending registration email", ex);
            throw ex;
        }
    }

    /**
     * @see EmailService#sendInviteEmail(InviteDTO, String)
     */
    @Override
    public void sendInviteEmail(final InviteDTO invite, final String lang) {
        try {
            Map<String, Object> model = enterInviteEmailData(invite);
            String template = "";
            if (lang.equals("en"))
                template = mailInviteEn;
            else if (lang.equals("ru"))
                template = mailInviteRu;
            MimeMessagePreparator preparator = getMimeMessagePreparator(template, model);
            mailSender.send(preparator);
            LOGGER.info("Invite email was sent");
        }
        catch (Exception ex){
            LOGGER.error("Error sending invite email", ex);
            throw ex;
        }
    }

    /**
     * @see EmailService#sendResetPasswordEmail(ResetTokenDTO, String)
     */
    @Override
    public void sendResetPasswordEmail(final ResetTokenDTO resetTokenDTO, final String lang) {
        try {
            Map<String, Object> model = enterResetPasswordEmailData(resetTokenDTO);
            String template = "";
            if (lang.equals("en"))
                template = mailRecoveryPasswordEn;
            else if (lang.equals("ru"))
                template = mailRecoveryPasswordRu;
            MimeMessagePreparator preparator = getMimeMessagePreparator(template, model);
            mailSender.send(preparator);
            LOGGER.info("Recovery password email was sent");
        }
        catch (Exception ex){
            LOGGER.error("Error sending recovery password email", ex);
            throw ex;
        }
    }

    /**
     * Getting mime message preparator
     * @param templateName - template name
     * @param model - data
     */
    @SuppressWarnings("unchecked")
    private MimeMessagePreparator getMimeMessagePreparator(final String templateName, final Map<String, Object> model){
        return mimeMessage -> {
            try {
                String from = (String) model.get(FROM);
                String to = (String) model.get(TO);
                String subject = (String) model.get(SUBJECT);
                List<String> bccList = (List<String>) model.get(BCC_LIST);
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
                message.setFrom(from);
                message.setTo(to);
                message.setSubject(subject);
                message.setSentDate(new Date());
                if (bccList != null)
                    for (String bcc : bccList)
                        message.addBcc(bcc);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", model);
                message.setText(text, true);
            }
            catch (MessagingException ex){
                LOGGER.error("Error preparing email");
                throw ex;
            }
        };
    }

    /**
     * Preparing data in registration mail.
     * @param user - information about user
     */
    private Map<String, Object> enterRegistrationEmailData(final TransientUserDTO user) {
        Map<String, Object> model = new HashMap<>();
        model.put("from", "Ealys");
        model.put("subject", "Congratulations!");
        model.put("to", user.getEmail());
        model.put("ccList", new ArrayList<>());
        model.put("bccList", new ArrayList<>());
        model.put("user", user);
        return  model;
    }

    /**
     * Preparing data in invite mail.
     * @param invite - information about invite
     */
    private Map<String, Object> enterInviteEmailData(final InviteDTO invite) {
        Map<String, Object> model = new HashMap<>();
        model.put("from", invite.getSender().getUsername());
        model.put("subject", "You need me!");
        model.put("to", invite.getEmail());
        model.put("ccList", new ArrayList<>());
        model.put("bccList", new ArrayList<>());
        model.put("invite", invite);
        return  model;
    }

    /**
     * Preparing data in reset password mail.
     * @param resetTokenDTO - information about reset token
     */
    private Map<String, Object> enterResetPasswordEmailData(final ResetTokenDTO resetTokenDTO) {
        Map<String, Object> model = new HashMap<>();
        model.put("from", "Ealys");
        model.put("subject", "Forgot password?");
        model.put("to", resetTokenDTO.getEmail());
        model.put("ccList", new ArrayList<>());
        model.put("bccList", new ArrayList<>());
        model.put("recoveryInfo", resetTokenDTO);
        return  model;
    }
}
