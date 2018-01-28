
package com.sam_solutions.ealys.dao;

import com.sam_solutions.ealys.entity.User;
import java.util.List;

/**
 * Interface for wotking with users table.
 */
public interface UserDAO extends GenericDAO<User, Long>{

    /**
     * Retrieves users from interval.
     * @param start - start position of interval.
     */
    List<User> getUsersFromInterval(int start);

    /**
     * Retrieve user by name.
     * @param name - user name.
     */
    User getUserByName(String name);

    /**
     * Retrieve user by email.
     * @param email - user email.
     */
    User getUserByEmail(String email);

    /**
     * Retrieves users with selected skill.
     * @param skillName - skill name.
     * @param start - postion of first result.
     */
    List<User> findUsersWithSkill(String skillName, int start);

    /**
     * Retrieves amount users with selected skill
     * @param skill - skill name
     */
    long getAmountUsersWithSkill(String skill);

    /**
     * Retrieves amount users.
     */
    long getAmountUsers();
}
