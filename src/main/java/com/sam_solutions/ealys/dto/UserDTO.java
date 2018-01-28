
package com.sam_solutions.ealys.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing UserDTO object.
 */
@Data
public class UserDTO {
    /**
     * User Id
     */
    private Long userId;

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
     * User last name
     */
    @Size(min = 2, max = 45)
    private String lastName;

    /**
     * User password
     */
    private String password;

    /**
     * User sex
     */
    @NotEmpty
    private String sex;

    /**
     * User skills
     */
    private List<SkillDTO> skills = new ArrayList<>();

    /**
     * User organization name
     */
    @Size(max = 255)
    private String organizationName;

    /**
     * User position in organization
     */
    @Size(max = 255)
    private String position;

    /**
     * Phone number
     */
    @Size(max = 20)
    private String phoneNumber;

    /**
     * User age
     */
    private Integer age;

    /**
     * About user
     */
    @Size(max = 1000)
    private String about;

    /**
     * User avatar
     */
    private String photo;

}
