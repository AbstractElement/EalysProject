
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.InviteDTO;

import java.util.List;

/**
 * Interface for working with project invites.
 */
public interface InviteService {
    /**
     * Adding invite in DB
     * @param invite - invite object
     */
    void save(InviteDTO invite);

    /**
     * Deleting invite from DB
     * @param invite - invite object
     */
    void delete(InviteDTO invite);

    /**
     * Retrieves invites by project id
     * @param projectId - project id
     */
    List<InviteDTO> findInvitesByProjectId(Long projectId);

    /**
     * @param email - user email
     * @param key - activating token
     */
    InviteDTO checkInvite(String email, String key);

    /**
     * Generating token
     */
    String generateKey();

    /**
     * Checking invite is exist
     * @param projectId - project id
     * @param email - user email
     */
    boolean inviteIsExist(Long projectId, String email);

    /**
     * Updating invite in DB
     * @param invite - invite object
     */
    void update(InviteDTO invite);
}
