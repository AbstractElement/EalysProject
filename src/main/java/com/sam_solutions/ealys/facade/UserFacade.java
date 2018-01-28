
package com.sam_solutions.ealys.facade;

import com.sam_solutions.ealys.dto.SkillDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import java.util.List;

/**
 * Interface for working with users.
 */
public interface UserFacade {
    /**
     * Retrieve user by name
     * @param name - user name
     */
    UserDTO findUserByName(String name);

    /**
     * Retrieve user by ID
     * @param id - user id
     */
    UserDTO findUserById(Long id);

    /**
     * Retrieves users from the interval with selected skill
     * @param skillName - skill name
     * @param pageNumber - number of selected page
     */
    List<UserDTO> findUsersWithSkill(String skillName, int pageNumber);

    /**
     * Updating user
     * @param user - user object
     */
    void updateUser(UserDTO user);

    /**
     * Retrieves users from the interval
     * @param pageNumber - number of selected page
     */
    List<UserDTO> getUsersFromInterval(int pageNumber);

    /**
     * Retrieves all skills
     */
    List<SkillDTO> getAllSkills();

    /**
     * Deleting user skill by id
     * @param id - skill id
     */
    void deleteUserSkillById(Long id);

    /**
     * Adding new skill for user
     * @param skillName - skill name
     */
    SkillDTO addNewSkills(String skillName);

    /**
     * Changing user password
     * @param passArr - contains: old password, new password, confirmed new password
     */
    boolean changePassword(String[] passArr);
}
