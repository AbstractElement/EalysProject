
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.UserDTO;

import java.util.List;

/**
 * Inteface for working with users.
 */
public interface UserService {
    /**
     * Add new user
     * @param user - user object
     */
    void addUser(UserDTO user);

    /**
     * Retrieves users from the interval
     * @param start - position of the first result
     */
    List<UserDTO> getUsersFromInterval(int start);

    /**
     * Retrive user by id
     * @param id - user id
     */
    UserDTO getUserById(Long id);

    /**
     * Retrieves user by name
     * @param name - user name
     */
    UserDTO getUserByName(String name);

    /**
     * Retrieves user by email
     * @param email - user email
     */
    UserDTO getUserByEmail(String email);

    /**
     * Getting current user object
     */
    UserDTO getCurrentUser();

    /**
     * Checking username is exist
     * @param username - username
     */
    boolean containsUsername(String username);

    /**
     * Checking email is exist
     * @param email - user email
     */
    boolean containsEmail(String email);

    /**
     * Deleting user
     * @param user - user object
     */
    void deleteUser(UserDTO user);

    /**
     * Updating user
     * @param user - user object
     */
    void updateUser(UserDTO user);

    /**
     * Retrieves users with skill from the interval
     * @param skillName - skill name
     * @param start - position of the first result
     */
    List<UserDTO> findUsersWithSkill(String skillName, int start);

    /**
     * Retrieves amount users with selected skill
     * @param skill - skill name
     */
    long getAmountUsersWithSkill(String skill);

    /**
     * Retrieves amount users
     */
    long getAmountUsers();
}
