
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dao.UserDAO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.entity.User;
import com.sam_solutions.ealys.service.UserService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with users.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(User.class);

    /**
     * For working with users table
     */
    private final UserDAO userDAO;

    /**
     * For converting objects
     */
    private final ConversionService conversionService;

    @Autowired
    public UserServiceImpl(final UserDAO userDAO, final ConversionService conversionService) {
        this.userDAO = userDAO;
        this.conversionService = conversionService;
    }

    /**
     * @see UserService#addUser(UserDTO)
     * @param user - user object
     */
    @Override
    @Transactional
    public void addUser(final UserDTO user){
        try {
            userDAO.create(conversionService.convert(user, User.class));
            LOGGER.info("User was added, id: " + user.getUserId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error adding user", ex);
            throw ex;
        }
    }

    /**
     * @see UserService#getUsersFromInterval(int)
     * @param start - position of the first result
     */
    @Override
    @Transactional
    public List<UserDTO> getUsersFromInterval(final int start) {
        try {
            LOGGER.info("Getting users from the interval");
            List<User> users = userDAO.getUsersFromInterval(start);
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User user : users)
                userDTOs.add(conversionService.convert(user, UserDTO.class));
            return userDTOs;
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting users from the interval", ex);
            throw ex;
        }
    }

    /**
     * @see UserService#getUserById(Long)
     * @param id - user id
     */
    @Override
    @Transactional
    public UserDTO getUserById(final Long id) {
        try{
            LOGGER.info("Getting user by id: " + id);
            return conversionService.convert(userDAO.read(User.class, id), UserDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting user by id: " + id, ex);
            throw ex;
        }
    }

    /**
     * @see UserService#getUserByName(String)
     * @param name - user name
     */
    @Override
    @Transactional
    public UserDTO getUserByName(final String name){
        try{
            LOGGER.info("Getting user by name: " + name);
            return conversionService.convert(userDAO.getUserByName(name), UserDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting user by name: " + name, ex);
            throw ex;
        }
    }

    /**
     * @see UserService#getUserByEmail(String)
     * @param email - user email
     */
    @Override
    @Transactional
    public UserDTO getUserByEmail(final String email){
        try{
            LOGGER.info("Getting user by email: " + email);
            return conversionService.convert(userDAO.getUserByEmail(email), UserDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting user by email: " + email, ex);
            throw ex;
        }

    }

    /**
     * @see UserService#containsUsername(String)
     * @param username - username
     */
    @Override
    @Transactional
    public boolean containsUsername(final String username) {
        try{
            LOGGER.info("Check username in DB: " + username);
            return userDAO.getUserByName(username) != null;
        }
        catch (JDBCException ex){
            LOGGER.error("Error checking username in DB: " + username, ex);
            throw ex;
        }

    }

    /**
     * @see UserService#containsEmail(String)
     * @param email - user email
     * @return
     */
    @Override
    @Transactional
    public boolean containsEmail(final String email){
        try{
            LOGGER.info("Checking email in DB: " + email);
            return userDAO.getUserByEmail(email) != null;
        }
        catch (JDBCException ex){
            LOGGER.error("Error checking email in DB: " + email, ex);
            throw ex;
        }

    }

    /**
     * @see UserService#deleteUser(UserDTO)
     * @param user - user object
     */
    @Override
    @Transactional
    public void deleteUser(final UserDTO user){
        try{
            LOGGER.info("Deleting user, id: " + user.getUserId());
            userDAO.delete(conversionService.convert(user, User.class));
        }
        catch (JDBCException ex){
            LOGGER.error("Error deleting user, id: " + user.getUserId(), ex);
            throw ex;
        }

    }

    /**
     * @see UserService#updateUser(UserDTO)
     * @param user - user object
     */
    @Override
    @Transactional
    public void updateUser(final UserDTO user) {
        try{
            LOGGER.info("Updating user, id: " + user.getUserId());
            userDAO.update(conversionService.convert(user, User.class));
        }
        catch (JDBCException ex){
            LOGGER.error("Error updating user, id: " + user.getUserId(), ex);
            throw ex;
        }
    }

    /**
     * @see UserService#findUsersWithSkill(String, int)
     * @param skillName - skill name
     * @param start - position of the first result
     */
    @Override
    @Transactional
    public List<UserDTO> findUsersWithSkill(final String skillName, final int start) {
        try {
            List<User> users = userDAO.findUsersWithSkill(skillName, start);
            LOGGER.info("Users with selected skill was found, skillP: " + skillName);
            List<UserDTO> userDTOList = new ArrayList<>();
            for (User user : users)
                userDTOList.add(conversionService.convert(user, UserDTO.class));
            LOGGER.info("Users was converted to DTO");
            return userDTOList;
        }
        catch (JDBCException ex){
            LOGGER.error("Error finding users with selected skill, skill: " + skillName, ex);
            throw ex;
        }
    }

    /**
     * @see UserService#getAmountUsersWithSkill(String)
     * @param skill - skill name
     * @return
     */
    @Override
    @Transactional
    public long getAmountUsersWithSkill(final String skill) {
        try{
            LOGGER.info("Get amount users with skill: " + skill);
            return userDAO.getAmountUsersWithSkill(skill);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting amount users with skill: " + skill, ex);
            throw ex;
        }
    }

    /**
     * @see UserService#getAmountUsers()
     */
    @Override
    @Transactional
    public long getAmountUsers() {
        try{
            LOGGER.info("Get amount users");
            return userDAO.getAmountUsers();
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting amount users", ex);
            throw ex;
        }
    }

    /**
     * @see UserService#getCurrentUser()
     * @return
     */
    @Override
    @Transactional
    public UserDTO getCurrentUser() {
        try{
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            LOGGER.info("Get current user");
            return conversionService.convert(userDAO.getUserByName(name), UserDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting current user");
            throw ex;
        }
    }
}
