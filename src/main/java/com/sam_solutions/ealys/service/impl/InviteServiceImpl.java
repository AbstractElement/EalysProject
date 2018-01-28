
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dao.InviteDAO;
import com.sam_solutions.ealys.dto.InviteDTO;
import com.sam_solutions.ealys.entity.Invite;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.service.InviteService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class for working with projects invites.
 */
@Service
public class InviteServiceImpl implements InviteService {
    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(Project.class);

    /**
     * @see InviteDAO
     */
    private final InviteDAO inviteDAO;

    /**
     * @see ConversionService
     */
    private final ConversionService conversionService;

    @Autowired
    public InviteServiceImpl(final InviteDAO inviteDAO, final ConversionService conversionService) {
        this.inviteDAO = inviteDAO;
        this.conversionService = conversionService;
    }

    /**
     * @see InviteService#save(InviteDTO)
     * @param invite - invite object
     */
    @Override
    @Transactional
    public void save(final InviteDTO invite){
        try{
            inviteDAO.create(conversionService.convert(invite, Invite.class));
            LOGGER.info("Invite was saved");
        }
        catch (JDBCException ex){
            LOGGER.error("Error saving invite", ex);
            throw ex;
        }
    }

    /**
     * @see InviteService#delete(InviteDTO)
     * @param invite - invite object
     */
    @Override
    @Transactional
    public void delete(final InviteDTO invite){
        try{
            inviteDAO.delete(conversionService.convert(invite, Invite.class));
            LOGGER.info("Invite was deleted");
        }
        catch (JDBCException ex){
            LOGGER.error("Error deleting invite", ex);
            throw ex;
        }
    }

    /**
     * @see InviteService#findInvitesByProjectId(Long)
     * @param projectId - project id
     */
    @Override
    @Transactional
    public List<InviteDTO> findInvitesByProjectId(final Long projectId) {
        try{
            LOGGER.info("Finding invites by project id: " + projectId);
            List<Invite> invites = inviteDAO.findInvitesByProjectId(projectId);
            List<InviteDTO> inviteDTOS = new ArrayList<>();
            for (Invite invite : invites)
                inviteDTOS.add(conversionService.convert(invite, InviteDTO.class));
            return inviteDTOS;
        }
        catch (JDBCException ex){
            LOGGER.error("Error finding invites by project id: " + projectId, ex);
            throw ex;
        }
    }

    /**
     * @see InviteService#checkInvite(String, String)
     * @param email - user email
     * @param key - activating token
     */
    @Override
    @Transactional
    public InviteDTO checkInvite(final String email, final String key) {
        try{
            LOGGER.info("Checking invite information");
            return conversionService.convert(inviteDAO.checkInvite(email, key), InviteDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error checking invite information", ex);
            throw ex;
        }
    }

    /**
     * @see InviteService#update(InviteDTO)
     * @param invite - invite object
     */
    @Override
    @Transactional
    public void update(final InviteDTO invite) {
        try{
            inviteDAO.update(conversionService.convert(invite, Invite.class));
            LOGGER.info("Invite was updated, id: " + invite.getInviteId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error updating invite, id: " + invite.getInviteId(), ex);
            throw ex;
        }
    }

    /**
     * @see InviteService#inviteIsExist(Long, String)
     * @param projectId - project id
     * @param email - user email
     */
    @Override
    @Transactional
    public boolean inviteIsExist(final Long projectId, final String email) {
        try {
            Invite invite = inviteDAO.checkInvite(projectId, email);
            if (invite != null) {
                LOGGER.info("This user was invited on this project");
                return true;
            }
            else {
                LOGGER.info("This user wasn't invited on this project");
                return false;
            }
        }
        catch (JDBCException ex){
            LOGGER.error("Error checking user invite for this project", ex);
            throw ex;
        }
    }

    /**
     * @see InviteService#generateKey()
     */
    @Override
    public String generateKey() {
        return UUID.randomUUID().toString();
    }
}
