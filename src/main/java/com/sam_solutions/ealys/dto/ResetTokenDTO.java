
package com.sam_solutions.ealys.dto;

import com.sam_solutions.ealys.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * Class for storing ResetTokenDTO object.
 */
@Data
public class ResetTokenDTO {
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
     * Date of create
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
    private UserDTO user;

    /**
     * Link state
     */
    private Byte enabled;
}
