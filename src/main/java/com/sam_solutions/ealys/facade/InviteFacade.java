
package com.sam_solutions.ealys.facade;

import com.sam_solutions.ealys.dto.InviteDTO;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Interface for working with projects invites.
 */
public interface InviteFacade {
    /**
     * Generate invite on project for user.
     * @param projectId - project id.
     * @param email - user email
     * @param lang - current language
     */
    InviteDTO inviteOnProject(Long projectId, String email, String lang);

    /**
     * Retrieves invites by project id
     * @param id - project id
     */
    List<InviteDTO> findInvitesByProjectId(Long id);

    /**
     * Activating key of project
     * @param key - generated token
     */
    boolean keyActivate(String key);
}
