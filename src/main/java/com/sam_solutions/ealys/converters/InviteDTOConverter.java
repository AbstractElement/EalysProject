
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.InviteDTO;
import com.sam_solutions.ealys.entity.Invite;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting InviteDTO class to Invite class.
 */
public class InviteDTOConverter implements Converter<InviteDTO, Invite> {

    /**
     * Converting InviteDTO object to Invite object
     * @param inviteDTO - InviteDTO object
     */
    @Override
    public Invite convert(final InviteDTO inviteDTO) {
        Invite invite = new Invite();
        invite.setInviteId(inviteDTO.getInviteId());
        invite.setEmail(inviteDTO.getEmail());
        invite.setToken(inviteDTO.getToken());
        invite.setProject(new ProjectDTOConverter().convert(inviteDTO.getProject()));
        invite.setSender(new UserDTOConverter().convert(inviteDTO.getSender()));
        invite.setDateOfDeactivating(inviteDTO.getDateOfDeactivating());
        return invite;
    }
}
