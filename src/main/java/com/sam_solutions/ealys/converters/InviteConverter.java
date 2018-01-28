
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.InviteDTO;
import com.sam_solutions.ealys.entity.Invite;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting Invite class to InviteDTO class.
 */
public class InviteConverter implements Converter<Invite, InviteDTO>{

    /**
     * Converting Invite object to InviteDTO object
     * @param invite - Invite object.
     */
    @Override
    public InviteDTO convert(final Invite invite) {
        InviteDTO inviteDTO = new InviteDTO();
        inviteDTO.setInviteId(invite.getInviteId());
        inviteDTO.setEmail(invite.getEmail());
        inviteDTO.setToken(invite.getToken());
        inviteDTO.setProject(new ProjectConverter().convert(invite.getProject()));
        inviteDTO.setSender(new UserConverter().convert(invite.getSender()));
        inviteDTO.setDateOfDeactivating(invite.getDateOfDeactivating());
        return inviteDTO;
    }

}
