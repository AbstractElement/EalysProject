
package com.sam_solutions.ealys.controllers;

import com.sam_solutions.ealys.dto.ImageDTO;
import com.sam_solutions.ealys.dto.InviteDTO;
import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RiskDTO;
import com.sam_solutions.ealys.dto.SkillDTO;
import com.sam_solutions.ealys.dto.TransientUserDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.facade.ImageFacade;
import com.sam_solutions.ealys.facade.InviteFacade;
import com.sam_solutions.ealys.facade.PaginationFacade;
import com.sam_solutions.ealys.facade.ProjectFacade;
import com.sam_solutions.ealys.facade.RegistrationFacade;
import com.sam_solutions.ealys.facade.RiskFacade;
import com.sam_solutions.ealys.facade.UserFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * REST requests.
 */
@Controller
@RequestMapping("/api")
public class RESTController {
    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(RESTController.class);

    /**
     * Interface for working with users
     */
    private final UserFacade userFacade;

    /**
     * Interface for working with projects
     */
    private final ProjectFacade projectFacade;

    /**
     * Interface for working with project risks
     */
    private final RiskFacade riskFacade;

    /**
     * Interface for working with images
     */
    private final ImageFacade imageFacade;

    /**
     * Interface for working with registration
     */
    private final RegistrationFacade registrationFacade;

    /**
     * Interface for working with project invite
     */
    private final InviteFacade inviteFacade;

    /**
     * Interface for working with pagination
     */
    private final PaginationFacade paginationFacade;

    @Autowired
    public RESTController(final UserFacade userFacade, final ProjectFacade projectFacade, final RiskFacade riskFacade,
                          final ImageFacade imageFacade, final RegistrationFacade registrationFacade,
                          final InviteFacade inviteFacade, final PaginationFacade paginationFacade) {
        this.userFacade = userFacade;
        this.projectFacade = projectFacade;
        this.riskFacade = riskFacade;
        this.imageFacade = imageFacade;
        this.registrationFacade = registrationFacade;
        this.inviteFacade = inviteFacade;
        this.paginationFacade = paginationFacade;
    }

    /**
     * Removing user skill by skill id.
     * @param id - skill id.
     * @param modelMap - response with error label
     * @throws Exception - general exception
     */
    @ResponseBody
    @RequestMapping(value = "/skill/{id}", method = RequestMethod.DELETE)
    public void removeUserSkill(@PathVariable final Long id,
                                final ModelMap modelMap) throws Exception{
        try {
            userFacade.deleteUserSkillById(id);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
        }
    }

    /**
     * Adding user skill.
     * @param skillName - Name of user skill.
     * @throws Exception - general exception
     */
    @ResponseBody
    @RequestMapping(value = "/skill", method = RequestMethod.POST)
    public SkillDTO addUserSkill(@RequestBody final String skillName) throws Exception{
        try {
            return userFacade.addNewSkills(skillName);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Creating new project.
     * @param newProject - object of project.
     * @param error - errors in input data about project.
     * @param modelMap - response with error label
     * @return - saved project. If exception - null.
     */
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public @ResponseBody ProjectDTO addNewProject(@Valid @RequestBody final ProjectDTO newProject,
                                                  final BindingResult error,
                                                  final ModelMap modelMap){
        try {
            if (error.hasErrors()) {
                newProject.setErrors(error.getFieldErrors());
                return newProject;
            }
            return projectFacade.saveProject(newProject);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return null;
        }
    }

    /**
     * Adding new risk in project.
     * @param newRisk - object of risk.
     * @param projectId - project id.
     * @param modelMap - response with error label.
     * @param bindingResult - errors in input data about risk
     * @return - saved risk. If exception - null.
     */
    @ResponseBody
    @RequestMapping(value = "/risk/{projectId}", method = RequestMethod.POST)
    public RiskDTO addNewRisk(@PathVariable final Long projectId,
                              @Valid @RequestBody final RiskDTO newRisk,
                              final BindingResult bindingResult,
                              final ModelMap modelMap){
        try {
            if (bindingResult.hasErrors()){
                newRisk.setErrors(bindingResult.getFieldErrors());
                return newRisk;
            }
            return riskFacade.saveRisk(newRisk, projectId);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return null;
        }
    }

    /**
     * Saving updated project.
     * @param updateProject - object of project.
     * @param bindingResult - errors in input data about project.
     * @param modelMap - response with error label.
     */
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public ProjectDTO saveUpdatedProject(@Valid @RequestBody final ProjectDTO updateProject,
                                         final BindingResult bindingResult,
                                         final ModelMap modelMap){
        try {
            if (bindingResult.hasErrors()) {
                updateProject.setErrors(bindingResult.getFieldErrors());
                return updateProject;
            }
            return projectFacade.updateProject(updateProject);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return null;
        }
    }

    /**
     * Removing risk from project.
     * @param id - risk id.
     * @param modelMap - response with error label.
     * @throws Exception - general exception
     * @return - risk id.
     */
    @ResponseBody
    @RequestMapping(value = "/risk/{id}", method = RequestMethod.DELETE)
    public void removeRisk(@PathVariable final Long id,
                           final ModelMap modelMap) throws Exception{
        try {
            riskFacade.deleteRisk(id);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
        }
    }

    /**
     * Saving changed risk.
     * @param projectId - project id.
     * @param id - risk id.
     * @param desc -  risk description.
     * @param modelMap - response with error label.
     * @return - updated risk object.
     */
    @ResponseBody
    @RequestMapping(value = "project/{projectId}/risk/{id}", method = RequestMethod.PUT)
    public RiskDTO saveChangeRisk(@PathVariable final Long projectId,
                                  @PathVariable final Long id,
                                  @RequestBody final String desc,
                                  final ModelMap modelMap){
        try {
            RiskDTO riskDTO = riskFacade.findRiskByRiskId(id);
            riskDTO.setDescription(desc);
            riskFacade.updateRisk(riskDTO, projectId);
            return riskDTO;
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return null;
        }
    }

    /**
     * Risk calculating by danger level.
     * @param projectId - project id.
     * @param modelMap - response with error label.
     * @return - array of integer values
     */
    @ResponseBody
    @RequestMapping(value = "/chart/{projectId}", method = RequestMethod.GET)
    public int[] getChart(@PathVariable final Long projectId,
                          final ModelMap modelMap){
        try{
            return riskFacade.valuesForChart(projectId);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return new int[10];
        }
    }

    /**
     * Email inviting on the project.
     * @param email - email destination.
     * @param modelMap - response with error label.
     * @param projectId - project id.
     * @param lang - current language.
     * @return - saved invite object.
     */
    @ResponseBody
    @RequestMapping(value = "/project/invite", method = RequestMethod.GET)
    public InviteDTO inviteOnProject(@RequestParam final Long projectId,
                                     @RequestParam final String email,
                                     @CookieValue(value = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE", defaultValue = "en") final String lang,
                                     final ModelMap modelMap){
        try {
            return inviteFacade.inviteOnProject(projectId, email, lang);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return null;
        }
    }

    /**
     * Remove project photo from directory
     * @param projectId - project id.
     * @param data - object with information about image.
     */
    @ResponseBody
    @RequestMapping(value = "/project/{projectId}/image", method = RequestMethod.DELETE)
    public void removePhoto(@PathVariable final Long projectId,
                            @RequestBody final ImageDTO data){
        try {
            imageFacade.removeProjectPhoto(projectId, data);
        }
        catch (Exception ex){
            LOGGER.error(ex);
        }
    }

    /**
     * Return projects from interval.
     * @param page - page number.
     */
    @ResponseBody
    @RequestMapping(value = "/pageProjects", method = RequestMethod.GET)
    public List<ProjectDTO> pageProjects(@RequestParam("page") final int page){
        try{
            return projectFacade.findLimitProjects(page);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Return project risks from interval.
     * @param page - page number.
     * @param id - project id.
     */
    @ResponseBody
    @RequestMapping(value = "/project/{id}/pageRisks", method = RequestMethod.GET)
    public List<RiskDTO> pageRisks(@RequestParam("page") final int page,
                                                 @PathVariable final Long id){
        try{
            return riskFacade.findLimitRisks(id, page);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Checking user data in registration form.
     * @param registrationForm - object with data about registration user.
     * @param bindingResult - errors input data registrationForm.
     * @param lang -current language
     */
    @ResponseBody
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public TransientUserDTO newUser(@Valid @RequestBody final TransientUserDTO registrationForm,
                                                  final BindingResult bindingResult,
                                                  @CookieValue(value = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE", defaultValue = "en") final String lang) throws Exception{
        try {
            if (bindingResult.hasErrors()) {
                registrationForm.setErrors(bindingResult.getFieldErrors());
                return registrationForm;
            }
            return registrationFacade.registrationUser(registrationForm, lang);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Return users on the page from the interval.
     * @param pageNumber - page number.
     */
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<UserDTO> pageUsers(@RequestParam("page") final int pageNumber){
        try {
            return userFacade.getUsersFromInterval(pageNumber);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Return users with selected skill on the page.
     * @param skillName - skill name.
     * @param pageNumber - number of selected page
     */
    @ResponseBody
    @RequestMapping(value = "/user/skill", method = RequestMethod.GET)
    public List<UserDTO> getUsersWithSkill(@RequestParam("name") final String skillName,
                                           @RequestParam("page") final int pageNumber){
        try{
            return userFacade.findUsersWithSkill(skillName, pageNumber);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * @return - amount pages with projects.
     */
    @ResponseBody
    @RequestMapping(value = "/amountProjectsPages", method = RequestMethod.GET)
    public int getAmountProjectsPages(){
        try {
            UserDTO user = userFacade.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
            return paginationFacade.amountProjectsPages(user.getUserId());
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return 0;
        }
    }

    /**
     * @return - amount pages with users
     */
    @ResponseBody
    @RequestMapping(value = "/amountUsersPages", method = RequestMethod.GET)
    public int getAmountUsersPages(){
        try {
            return paginationFacade.amountUsersPages();
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return 0;
        }
    }

    /**
     * @param skill - name of selected skill
     * @return - amount pages users with selected skill
     */
    @ResponseBody
    @RequestMapping(value = "/amountUsersWithSkillPages", method = RequestMethod.GET)
    public int getAmountUsersWithSkillPages(@RequestParam("skill") final String skill){
        try {
            return paginationFacade.amountUsersWithSkillPages(skill);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return 0;
        }
    }

    /**
     * @param projectId - project id
     * @return - amount pages with project risks
     */
    @ResponseBody
    @RequestMapping(value = "/amountRisksPages", method = RequestMethod.GET)
    public int getAmountRisksPages(@RequestParam(value = "project") final Long projectId){
        try {
            return paginationFacade.amountRisksPages(projectId);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return 0;
        }
    }

    /**
     * Set new password.
     * @param passArr - passwords array [oldPassword, newPassword, confirmNewPassword]
     * @return - true - if new password was saved, false - new password was not saved.
     */
    @ResponseBody
    @RequestMapping(value = "/newPass", method = RequestMethod.POST)
    public boolean changePassword(@RequestBody final String[] passArr){
        try{
            return userFacade.changePassword(passArr);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return false;
        }
    }
}
