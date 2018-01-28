
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.TransientUserDTO;
import com.sam_solutions.ealys.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;

public class UserDTOToTransientUserDTOConverter implements Converter<UserDTO, TransientUserDTO> {

    /**
     * Converting UserDTO object to TransientUserDTO object
     * @param user - UserDTo object
     */
    @Override
    public TransientUserDTO convert(final UserDTO user) {
        TransientUserDTO transientUserDTO = new TransientUserDTO();
        transientUserDTO.setUsername(user.getUsername());
        transientUserDTO.setEmail(user.getEmail());
        transientUserDTO.setFirstName(user.getFirstName());
        transientUserDTO.setLastName(user.getLastName());
        transientUserDTO.setPassword(user.getPassword());
        transientUserDTO.setSex(user.getSex());
        transientUserDTO.setPhoto(user.getPhoto());
        return transientUserDTO;
    }
}
