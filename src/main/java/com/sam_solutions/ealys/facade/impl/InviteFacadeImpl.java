
package com.sam_solutions.ealys.facade.impl;

import com.sam_solutions.ealys.dto.InviteDTO;
import com.sam_solutions.ealys.dto.RoleOnProjectDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.facade.InviteFacade;
import com.sam_solutions.ealys.service.EmailService;
import com.sam_solutions.ealys.service.InviteService;
import com.sam_solutions.ealys.service.ProjectService;
import com.sam_solutions.ealys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Class for working with projects invites.
 */
@Component
public class InviteFacadeImpl implements InviteFacade{
    /**
     * Service for working with projects invites
     */
    private final InviteService inviteService;

    /**
     * Service for working with users
     */
    private final UserService userService;

    /**
     * Service for working with user projects
     */
    private final ProjectService projectService;

    /**
     * Service for working with email messages
     */
    private final EmailService emailService;

    /**
     * User role on project - GUEST (Property value: util.properties)
     */
    @Value("${role.guest}")
    private String roleGuest;

    /**
     * Lifetime of invite (Property value: util.properties)
     */
    @Value("${lifetime.invite}")
    private long lifetimeInvite;

    @Autowired
    public InviteFacadeImpl(final InviteService inviteService, final UserService userService,
                            final ProjectService projectService, final EmailService emailService) {
        this.inviteService = inviteService;
        this.userService = userService;
        this.projectService = projectService;
        this.emailService = emailService;
    }

    /**
     * @see InviteFacade#findInvitesByProjectId(Long)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<InviteDTO> findInvitesByProjectId(final Long id) {
        return inviteService.findInvitesByProjectId(id);
    }

    /**
     * @see InviteFacade#keyActivate(String)
     */
    @Override
    public boolean keyActivate(final String token) {
        UserDTO user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        InviteDTO invite = inviteService.checkInvite(user.getEmail(), token);
        if (invite != null && projectService.findUserProject(invite.getProject().getProjectId(), user.getUserId()) == null){
            RoleOnProjectDTO roleOnProjectDTO = new RoleOnProjectDTO();
            roleOnProjectDTO.setUser(user);
            roleOnProjectDTO.setRoleOnProject(roleGuest);
            invite.getProject().getUsers().add(roleOnProjectDTO);
            projectService.updateProject(invite.getProject());
            return true;
        }
        else
            return false;
    }

    /**
     * @see InviteFacade#inviteOnProject(Long, String, String)
     */
    @Override
    public InviteDTO inviteOnProject(final Long projectId, final String email, final String lang){
        if (!inviteService.inviteIsExist(projectId, email)) {
            InviteDTO inviteDTO = new InviteDTO();
            inviteDTO.setEmail(email);
            inviteDTO.setProject(projectService.getProjectById(projectId));
            inviteDTO.setSender(userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
            inviteDTO.setToken(inviteService.generateKey());
            inviteDTO.setDateOfDeactivating(new Date(new Date().getTime() + lifetimeInvite));
            inviteService.save(inviteDTO);
            emailService.sendInviteEmail(inviteDTO, lang);
            return inviteDTO;
        }
        else
            return null;
    }
}
