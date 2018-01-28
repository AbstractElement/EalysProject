
package com.sam_solutions.ealys.dao;

import com.sam_solutions.ealys.entity.Invite;

import java.util.List;

/**
 * Interface for working witn project invites table in DB.
 */
public interface InviteDAO extends GenericDAO<Invite, Long> {

    /**
     * Retrieves invites by project id.
     * @param projectId - project id.
     */
    List<Invite> findInvitesByProjectId(Long projectId);

    /**
     * Checking invite in DB by email and key.
     * @param email - addressee.
     * @param key - project key.
     * @return - invite object or null (if this invite don't exist in DB).
     */
    Invite checkInvite(String email, String key);

    /**
     * Checking invite in DB by projectId and email.
     * @param projectId - project id.
     * @param email - addressee.
     * @return - invite object or null (if this invite don't exist in DB).
     */
    Invite checkInvite(Long projectId, String email);
}
