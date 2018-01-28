
package com.sam_solutions.ealys.entity;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Class for storing Invite object.
 */
@Data
public class Invite {
    /**
     * Invite ID
     */
    private Long inviteId;

    /**
     * User email
     */
    private String email;

    /**
     * Token
     */
    private String token;

    /**
     * Project
     */
    private Project project;

    /**
     * User-sender
     */
    private User sender;

    /**
     * Date of deactivating invite
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfDeactivating;
}
