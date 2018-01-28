
package com.sam_solutions.ealys.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing User object.
 */
@Data
public class User {
    /**
     * User ID
     */
    private Long userId;

    /**
     * User email
     */
    private String email;

    /**
     * User password
     */
    private String password;

    /**
     * Username
     */
    private String username;

    /**
     * User first name
     */
    private String firstName;

    /**
     * User last name
     */
    private String lastName;

    /**
     * User organization name
     */
    private String organizationName;

    /**
     * User position in organization
     */
    private String position;

    /**
     * User phone number
     */
    private String phoneNumber;

    /**
     * User sex
     */
    private String sex;

    /**
     * User age
     */
    private Integer age;

    /**
     * About user
     */
    private String about;

    /**
     * User avatar
     */
    private String photo;

    /**
     * User skills
     */
    private List<Skill> skills = new ArrayList<>();

    /**
     * User account state
     */
    private Byte enabled = 1;
}
