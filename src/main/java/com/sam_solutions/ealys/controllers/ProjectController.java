
package com.sam_solutions.ealys.controllers;

import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RiskDTO;
import com.sam_solutions.ealys.facade.InviteFacade;
import com.sam_solutions.ealys.facade.ProjectFacade;
import com.sam_solutions.ealys.facade.RiskFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  Controller for working with user projects.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(ProjectController.class);

    /**
     * Interface for working with projects
     */
    private final ProjectFacade projectFacade;

    /**
     * Interface for working with projects risks
     */
    private final RiskFacade riskFacade;

    /**
     * Interface for working with invites
     */
    private final InviteFacade inviteFacade;

    /**
     * Property value. User role on project (OWNER)
     */
    @Value("${role.owner}")
    private String roleOwner;

    /**
     * Property value. User role on project (GUEST)
     */
    @Value("${role.guest}")
    private String roleGuest;

    @Autowired
    public ProjectController(final ProjectFacade projectFacade, final RiskFacade riskFacade, final InviteFacade inviteFacade) {
        this.projectFacade = projectFacade;
        this.riskFacade = riskFacade;
        this.inviteFacade = inviteFacade;
    }

    /**
     * Redirect on "project" page for editing project.
     * Before redirecting, the user role checking on this project.
     * If user role not equals OWNER - display message (accessError) on the "project" page.
     * @param projectId - id of project which will be edit.
     * @param modelMap - response parameters.
     * @throws Exception - general exception.
     */
    @RequestMapping(value = "/update/{projectId}", method = RequestMethod.GET)
    public String updateProject(@PathVariable final Long projectId,
                                final ModelMap modelMap) throws Exception{
        try{
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            String access = projectFacade.checkUserAccessOnProject(projectId, userName);
            if (access.equals(roleOwner))
                modelMap.addAttribute("updateProject", projectFacade.findProjectById(projectId));
            else
                modelMap.addAttribute("accessError", true);
            return "project";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "project";
        }
    }

    /**
     * Redirect on the "projects" page.
     * Return all projects this user and create new DTO object of project.
     * @param modelMap - response attributes.
     * @param pageNumber - number of selected page.
     * @throws Exception - general exception.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAllProjects(final ModelMap modelMap,
                                 @RequestParam("pageNumber") final int pageNumber) throws Exception{
        try{
            modelMap.addAttribute("newProject", new ProjectDTO());
            modelMap.addAttribute("projects", projectFacade.findLimitProjects(pageNumber));
            modelMap.addAttribute("pageNumber", pageNumber);
            return "projects";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "project";
        }
    }

    /**
     * Redirect on "project" page where will be displaying information about project.
     * Before redirecting, the user role checking on this project.
     * If user role not equals OWNER or GUEST - display message (accessError) on "project" page.
     * @param id - project id.
     * @param modelMap - response attributes.
     * @throws Exception - general exception.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String read(@PathVariable final Long id,
                       final ModelMap modelMap) throws Exception {
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            String access = projectFacade.checkUserAccessOnProject(id, userName);
            if (access.equals(roleOwner) || access.equals(roleGuest)) {
                modelMap.addAttribute("project", projectFacade.findProjectById(id));
                modelMap.addAttribute("userRole", access);
            }
            else
                modelMap.addAttribute("accessError", true);
            return "project";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "project";
        }
    }

    /**
     * Redirect on "user" page where will be displaying project group and invites on project.
     * Before redirecting, the user role checking on this project.
     * If user role not equals OWNER or GUEST - display message (accessError) on "user" page.
     * Return: project group, project invites and selected project.
     * @param modelMap - response attributes.
     * @param projectId - id of selected project.
     * @throws Exception - general exception.
     */
    @RequestMapping(value = "/{projectId}/users", method = RequestMethod.GET)
    public String getProjectGroup(@PathVariable final Long projectId,
                                  final ModelMap modelMap) throws Exception{
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            String access = projectFacade.checkUserAccessOnProject(projectId, userName);
            if (access.equals(roleOwner) || access.equals(roleGuest)){
                if (access.equals(roleOwner)){
                    modelMap.addAttribute("userRole", access);
                    modelMap.addAttribute("inviteUsers", inviteFacade.findInvitesByProjectId(projectId));
                }
                ProjectDTO projectDTO = projectFacade.findProjectById(projectId);
                modelMap.addAttribute("projectGroup", projectDTO.getUsers());
                modelMap.addAttribute("project", projectDTO);
            }
            else
                modelMap.addAttribute("accessError", true);
            return "user";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "user";
        }
    }

    /**
     * Redirect on "risk" page where will br displaying project risks.
     * Before redirecting, the user role checking on this project.
     * If user role not equals OWNER or GUEST - display message (accessError) on "risk" page.
     * Return on the page: project risks, clean DTO object of risk and selected project.
     * @param projectId - id of selected project
     * @param modelMap - response attributes
     * @param pageNumber - number of selected page
     * @throws Exception - general exception
     */
    @RequestMapping(value = "/{projectId}/risks", method = RequestMethod.GET)
    public String getProjectRisks(@PathVariable final Long projectId,
                                  @RequestParam("pageNumber") final int pageNumber,
                                  final ModelMap modelMap) throws Exception{
        try{
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            String access = projectFacade.checkUserAccessOnProject(projectId, userName);
            if (access.equals(roleOwner) || access.equals(roleGuest)) {
                modelMap.addAttribute("projectRisks", riskFacade.findLimitRisks(projectId, pageNumber));
                modelMap.addAttribute("newRisk", new RiskDTO());
                modelMap.addAttribute("project", projectFacade.findProjectById(projectId));
                modelMap.addAttribute("pageNumber", pageNumber);
            }
            else
                modelMap.addAttribute("accessError", true);
            return "risk";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "risk";
        }
    }
    
    /**
     * Deleting a user from project.
     * If this user has an owner role on the project, that project deleting too.
     * @param projectId - project id.
     * Redirect to function getAllProjects (mapping: /project?pageNumber=1).
     */
    @RequestMapping(value = "/{projectId}/leave", method = RequestMethod.GET)
    public String leaveProject(@PathVariable final Long projectId){
        try {
            projectFacade.leaveProject(projectId);
            return "redirect:/project?pageNumber=1";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            throw ex;
        }
    }

    /**
     * Activation project invite.
     * If the invite doesn't activated - show error message.
     * @param projectId - project id.
     * @param invite - invite token.
     * @param modelMap - response attributes.
     */
    @RequestMapping(value = "/{id}/activate", method = RequestMethod.GET)
    public String inviteActivating(@PathVariable("id") final Long projectId,
                                   @RequestParam final String invite,
                                   final ModelMap modelMap){
        try{
            boolean isActivated = inviteFacade.keyActivate(invite);
            if (isActivated)
                modelMap.addAttribute("project", projectFacade.findProjectById(projectId));
            else
                modelMap.addAttribute("inviteActivatingError", true);
            return "project";
        }
        catch (Exception ex){
            LOGGER.error(ex);
            modelMap.addAttribute("exception", true);
            return "project";
        }
    }

    /**
     * Generate and upload result project pdf file.
     * @param projectId - project id.
     * @param lang - current language.
     */
    @RequestMapping(value = "/{id}/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> generatePdf(@PathVariable("id") final Long projectId,
                                              @CookieValue(value = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE", defaultValue = "en") final String lang){
        try {
            return projectFacade.getProjectPDF(projectId, lang);
        }
        catch (Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }
}
