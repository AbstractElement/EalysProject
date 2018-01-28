
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.entity.Skill;
import com.sam_solutions.ealys.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting User class to UserDTO class.
 */
public class UserConverter implements Converter<User, UserDTO>{

    /**
     * Converting User object to UserDTO object.
     * @param user - User object
     */
    @Override
    public UserDTO convert(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        userDTO.setOrganizationName(user.getOrganizationName());
        userDTO.setPosition(user.getPosition());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setSex(user.getSex());
        userDTO.setAge(user.getAge());
        userDTO.setAbout(user.getAbout());
        userDTO.setPhoto(user.getPhoto());
        for (Skill skill : user.getSkills())
            userDTO.getSkills().add(new SkillConverter().convert(skill));
        return userDTO;
    }
}
