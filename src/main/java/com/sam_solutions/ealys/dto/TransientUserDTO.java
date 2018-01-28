
package com.sam_solutions.ealys.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.FieldError;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing TransientUserDTO obejct.
 */
@Data
public class TransientUserDTO {
    /**
     * Username
     */
    @Size(min = 4, max = 25)
    private String username;

    /**
     * User email
     */
    @Email
    @NotEmpty
    @Size(max = 40)
    private String email;

    /**
     * User first name
     */
    @Size(min = 2, max = 45)
    private String firstName;

    /**
     * User second name
     */
    @Size(min = 2, max = 45)
    private String lastName;

    /**
     * User password
     */
    @Size(min = 8, max = 20)
    private String password;

    /**
     * Repeated user password
     */
    @Size(min = 8, max = 20)
    private String repeatedPassword;

    /**
     * User sex
     */
    @NotEmpty
    private String sex;

    /**
     * User avatar
     */
    private String photo;

    /**
     * User validation errors
     */
    private List<FieldError> errors = new ArrayList<>();
}
