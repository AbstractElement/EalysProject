
package com.sam_solutions.ealys.controllers;

import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.facade.ProjectFacade;
import com.sam_solutions.ealys.facade.UserFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Controller for working with users.
 */
@Controller
public class UserController{

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    /**
     * Interface for working with users
     */
    private final UserFacade userFacade;

    /**
     * Interface for working with projects
     */
    private final ProjectFacade projectFacade;

    @Autowired
    public UserController(final UserFacade userFacade, final ProjectFacade projectFacade) {
        this.userFacade = userFacade;
        this.projectFacade = projectFacade;
    }

    /**
     * Redirect to "user" page.
     * Return on the page: user information, user skills.
     * @param id - user id. If path variable id equals 0 - current user.
     * @param lang - current language.
     * @param modelMap - response attributes.
     * @throws Exception - general exception
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String read(@PathVariable final Long id,
                       @CookieValue(value = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE", defaultValue = "en") final String lang,
                       final ModelMap modelMap) throws Exception {
        try {
            if (id == 0) {
                UserDTO user = userFacade.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
                modelMap.addAttribute("user", user);
            } else {
                modelMap.addAttribute("user", userFacade.findUserById(id));
            }
            return "user";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "user";
        }
    }

    /**
     * Redirect to "user" page.
     * Return on the page users with selected skill.
     * @param skillName - name of user skill.
     * @param modelMap - response attributes.
     */
    @RequestMapping(value = "/user/skill", method = RequestMethod.GET)
    public String findUsersWithSkill(@RequestParam("skill") final String skillName,
                                      final ModelMap modelMap){
        try {
            modelMap.addAttribute("users", userFacade.findUsersWithSkill(skillName, 1));
            return "user";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "user";
        }
    }

    /**
     * Redirect to the "main" page.
     * Return on the page user projects.
     * @param modelMap - response attributes.
     * @throws Exception - general exception.
     */
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String userPage(final ModelMap modelMap) throws Exception{
        try {
            UserDTO currentUser = userFacade.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
            modelMap.addAttribute("user", currentUser);
            modelMap.addAttribute("projects", projectFacade.findLimitProjects(1));
            return "main";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "main";
        }
    }

    /**
     * Redirect to "user" page. Displaying user settings.
     * Return on page object od current user.
     * @param modelMap - response attributes.
     * @throws Exception - general exception.
     */
    @RequestMapping(value = "/user/settings", method = RequestMethod.GET)
    public String addInformationAboutUser(final ModelMap modelMap) throws Exception{
        try {
            modelMap.addAttribute("updateUser", userFacade
                    .findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
            return "user";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "user";
        }
    }

    /**
     * Redirect to function read (mapping: /user/{id}).
     * {id} - 0, for displaying information about current user.
     * Saving updated information about current user.
     * @param updateUser - object with updated information.
     * @param modelMap - response attributes.
     * @param bindingResult - errors input data updateUser.
     * @throws Exception - general exception.
     */
    @RequestMapping(value = "/user/settings/submit", method = RequestMethod.POST)
    public String saveInformationAboutUser(@Valid @ModelAttribute("updateUser") final UserDTO updateUser,
                                           final BindingResult bindingResult,
                                           final ModelMap modelMap) throws Exception{
        try {
            if (bindingResult.hasErrors()) {
                modelMap.addAttribute("updateUser", updateUser);
                return "user";
            }
            userFacade.updateUser(updateUser);
            return "redirect:/user/0";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return "redirect:/user/0";
        }
    }

    /**
     * Redirect to "user" page. Displaying all users.
     * Return on the page list of users.
     * @param modelMap - response attributes.
     * @param pageNumber - number of selected page.
     * @throws Exception - general exception.
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUsers(final ModelMap modelMap,
                              @RequestParam("pageNumber") final int pageNumber) throws Exception{
        try {
            modelMap.addAttribute("users", userFacade.getUsersFromInterval(pageNumber));
            modelMap.addAttribute("skills", userFacade.getAllSkills());
            modelMap.addAttribute("pageNumber", pageNumber);
            return "user";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "user";
        }
    }
}
