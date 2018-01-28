
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.SkillDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import com.sam_solutions.ealys.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting UserDTO class to User class.
 */
public class UserDTOConverter implements Converter<UserDTO, User> {

    /**
     * Converting UserDTO object to User object
     * @param userDTO - UserDTO object
     */
    @Override
    public User convert(final UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setOrganizationName(userDTO.getOrganizationName());
        user.setPosition(userDTO.getPosition());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setSex(userDTO.getSex());
        user.setAge(userDTO.getAge());
        user.setAbout(userDTO.getAbout());
        user.setPhoto(userDTO.getPhoto());
        for (SkillDTO skillDTO : userDTO.getSkills())
            user.getSkills().add(new SkillDTOConverter().convert(skillDTO));
        return user;
    }
}
