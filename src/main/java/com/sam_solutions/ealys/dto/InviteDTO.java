
package com.sam_solutions.ealys.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Class for storing invite DTO object.
 */
@Data
public class InviteDTO {
    /**
     * Invite id
     */
    private Long inviteId;

    /**
     * Recipient's email
     */
    @NotNull
    @Email
    private String email;

    /**
     * Project key
     */
    @NotNull
    private String token;

    /**
     * Project
     */
    @NotNull
    private ProjectDTO project;

    /**
     * Sender
     */
    @NotNull
    private UserDTO sender;

    /**
     * Date of creating invite
     */
    private Date dateOfDeactivating;
}
