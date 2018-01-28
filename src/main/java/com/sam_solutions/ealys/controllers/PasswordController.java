
package com.sam_solutions.ealys.controllers;

import com.sam_solutions.ealys.dto.ResetTokenDTO;
import com.sam_solutions.ealys.dto.TransientUserDTO;
import com.sam_solutions.ealys.facade.PasswordRecoveryFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Class controller for work with user password.
 */
@Controller
public class PasswordController {

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(PasswordController.class);

    /**
     * Class for working with user password.
     */
    private final PasswordRecoveryFacade passwordRecoveryFacade;

    @Autowired
    public PasswordController(final PasswordRecoveryFacade passwordRecoveryFacade) {
        this.passwordRecoveryFacade = passwordRecoveryFacade;
    }

    /**
     * Generate token for user and sent email message.
     * @param email - email address
     * @param lang - current language
     * @param modelMap - response parameters
     */
    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String generateToken(@RequestParam("email") final String email,
                                @CookieValue(value = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE", defaultValue = "en") final String lang,
                                final ModelMap modelMap){
        try{
            if (passwordRecoveryFacade.restorePass(email, lang))
                modelMap.addAttribute("sentEmailMes", true);
            else
                modelMap.addAttribute("restorePassError", true);
            modelMap.addAttribute("registrationForm", new TransientUserDTO());
            return "login";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("registrationForm", new TransientUserDTO());
            modelMap.addAttribute("exception", true);
            return "login";
        }
    }

    /**
     * Checking token and redirect on reset password page.
     * If token is not available - return error message.
     * @param token - user token for reset password
     * @param modelMap - response attributes
     * @param request - get user IP
     */
    @RequestMapping(value = "/resetPass", method = RequestMethod.GET)
    public String resetPassword(@RequestParam final String token,
                                final ModelMap modelMap,
                                final HttpServletRequest request){
        try{
            ResetTokenDTO resetTokenDTO = passwordRecoveryFacade.findByToken(token);
            TransientUserDTO userDTO = passwordRecoveryFacade.activateToken(resetTokenDTO, request.getRemoteAddr());
            if (userDTO != null)
                modelMap.addAttribute("resetUserPass", userDTO);
            else
                modelMap.addAttribute("resetUserPassError", true);
            return "resetPass";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "resetPass";
        }
    }

    /**
     * Checking input data, if it true - confirm new user password and redirect to login page,
     * false - return error message.
     * @param userDTO - object contains: password and confirm new password
     * @param modelMap - response attributes
     */
    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    public String confirmPassword(@ModelAttribute("resetUserPass") final TransientUserDTO userDTO,
                                  final ModelMap modelMap){
        try {
            if (passwordRecoveryFacade.confirmNewPass(userDTO)) {
                modelMap.addAttribute("registrationForm", new TransientUserDTO());
                modelMap.addAttribute("resetUserPassSuccessMes", true);
                return "login";
            }
            else{
                modelMap.addAttribute("resetUserPass", userDTO);
                modelMap.addAttribute("resetUserPassErrorNotEquals", true);
                return "resetPass";
            }
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "resetPass";
        }
    }
}
