
package com.sam_solutions.ealys.facade.impl;

import com.sam_solutions.ealys.dto.SkillDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.facade.UserFacade;
import com.sam_solutions.ealys.service.SkillService;
import com.sam_solutions.ealys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class for working with users processes.
 */
@Component
public class UserFacadeImpl implements UserFacade {
    /**
     * @see UserService
     */
    private final UserService userService;

    /**
     * @see SkillService
     */
    private final SkillService skillService;

    /**
     * For encoding passwords
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Amount users on page (Property value)
     */
    @Value("${users.on.page}")
    private int amountUsersOnPage;

    @Autowired
    public UserFacadeImpl(final UserService userService, final SkillService skillService, final PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.skillService = skillService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @see UserFacade#findUserByName(String)
     * @param name - user name
     */
    @Override
    public UserDTO findUserByName(final String name) {
        return userService.getUserByName(name);
    }

    /**
     * @see UserFacade#updateUser(UserDTO)
     * @param user - user object
     */
    @Override
    public void updateUser(final UserDTO user) {
        UserDTO userDTO = userService.getUserById(user.getUserId());
        user.setPhoto(userDTO.getPhoto());
        user.setSkills(userDTO.getSkills());
        userService.updateUser(user);
    }

    /**
     * @see UserFacade#deleteUserSkillById(Long)
     * @param id - skill id
     */
    @Override
    public void deleteUserSkillById(final Long id) {
        UserDTO user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        SkillDTO skill = skillService.getSkillById(id);
        user.getSkills().remove(skill);
        userService.updateUser(user);
    }

    /**
     * @see UserFacade#addNewSkills(String)
     * @param skillName - skill name
     */
    @Override
    public SkillDTO addNewSkills(final String skillName) {
        SkillDTO skill = skillService.getSkillByName(skillName);
        UserDTO user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (skill == null){
            skill = new SkillDTO();
            skill.setName(skillName);
        }
        user.getSkills().add(skill);
        userService.updateUser(user);
        return skillService.getSkillByName(skillName);
    }

    /**
     * @see UserFacade#getAllSkills()
     */
    @Override
    public List<SkillDTO> getAllSkills() {
        return skillService.getAllSkill();
    }

    /**
     * @see UserFacade#getUsersFromInterval(int)
     * @param pageNumber - number of selected page
     */
    @Override
    public List<UserDTO> getUsersFromInterval(final int pageNumber) {
        int start = pageNumber * amountUsersOnPage - amountUsersOnPage;
        return userService.getUsersFromInterval(start);
    }

    /**
     * @see UserFacade#findUsersWithSkill(String, int)
     * @param skillName - skill name
     * @param pageNumber - number of selected page
     */
    @Override
    public List<UserDTO> findUsersWithSkill(final String skillName, final int pageNumber) {
        int start = pageNumber * amountUsersOnPage - amountUsersOnPage;
        return userService.findUsersWithSkill(skillName, start);
    }

    /**
     * @see UserFacade#findUserById(Long)
     * @param id - user id
     */
    @Override
    public UserDTO findUserById(final Long id) {
        return userService.getUserById(id);
    }

    /**
     * @see UserFacade#changePassword(String[])
     * @param passArr - contains: old password, new password, confirmed new password
     */
    @Override
    public boolean changePassword(final String[] passArr) {
        UserDTO user = userService.getCurrentUser();
        if (user.getPassword().equals(passwordEncoder.encode(passArr[0])) && passArr[1].equals(passArr[2])){
            user.setPassword(passArr[1]);
            return true;
        }
        else return false;
    }
}
