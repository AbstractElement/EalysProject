
package com.sam_solutions.ealys.controllers;

import com.sam_solutions.ealys.dto.TransientUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller login page.
 */
@Controller
public class LoginController {

    /**
     * Redirect to the "login" page.
     * @param modelMap - response attributes
     * @param error - login errors
     */
    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String mainPage(final ModelMap modelMap,
                           @RequestParam(required = false) final String error){
        modelMap.addAttribute("registrationForm", new TransientUserDTO());
        if (error != null)
            modelMap.addAttribute("error", true);
        return "login";
    }
}
