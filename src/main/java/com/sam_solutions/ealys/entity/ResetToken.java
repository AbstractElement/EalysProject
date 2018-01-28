
package com.sam_solutions.ealys.entity;

import lombok.Data;

import java.util.Date;

/**
 * Class for storing ResetToken object.
 */
@Data
public class ResetToken {
    /**
     * Id
     */
    private Long tokenId;

    /**
     * User email
     */
    private String email;

    /**
     * Token
     */
    private String token;

    /**
     * Date of create request
     */
    private Date dateCreate;

    /**
     * Date of activate token
     */
    private Date dateActivate;

    /**
     * User IP
     */
    private String userIP;

    /**
     * Owner
     */
    private User user;

    /**
     * Link state
     */
    private Byte enabled;
}
