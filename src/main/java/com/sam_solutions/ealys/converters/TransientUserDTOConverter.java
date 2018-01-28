
package com.sam_solutions.ealys.converters;


import com.sam_solutions.ealys.dto.TransientUserDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;

public class TransientUserDTOConverter implements Converter<TransientUserDTO, UserDTO> {

    /**
     * Converting TransientUserDTO object to UserDTO object
     * @param transientUserDTO - TransientUserDTO object
     */
    @Override
    public UserDTO convert(final TransientUserDTO transientUserDTO) {
        UserDTO user = new UserDTO();
        user.setUsername(transientUserDTO.getUsername());
        user.setEmail(transientUserDTO.getEmail());
        user.setFirstName(transientUserDTO.getFirstName());
        user.setLastName(transientUserDTO.getLastName());
        user.setPassword(transientUserDTO.getPassword());
        user.setSex(transientUserDTO.getSex());
        user.setPhoto(transientUserDTO.getPhoto());
        return user;
    }
}
