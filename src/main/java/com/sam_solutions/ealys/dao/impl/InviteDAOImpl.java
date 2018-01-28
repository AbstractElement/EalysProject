
package com.sam_solutions.ealys.dao.impl;

import com.sam_solutions.ealys.dao.InviteDAO;
import com.sam_solutions.ealys.entity.Invite;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class for working witn project invites table in DB.
 */
@Repository
public class InviteDAOImpl extends GenericDAOImpl<Invite, Long> implements InviteDAO {
    /**
     * Return object who create hibernate session
     */
    private final SessionFactory sessionFactory;

    @Autowired
    public InviteDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *@see InviteDAO#findInvitesByProjectId(Long)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Invite> findInvitesByProjectId(final Long projectId) {
        return sessionFactory.getCurrentSession().createQuery("from Invite where project.projectId = :id")
                .setParameter("id", projectId).list();
    }

    /**
     * @see InviteDAO#checkInvite(String, String)
     */
    @Override
    public Invite checkInvite(final String email, final String token) {
        return (Invite) sessionFactory.getCurrentSession().createQuery("from Invite where email = :email and token = :token "
                + "and dateOfDeactivating > current_timestamp ")
                .setParameter("email", email).setParameter("token", token).uniqueResult();
    }

    /**
     * @see InviteDAO#checkInvite(Long, String)
     */
    @Override
    public Invite checkInvite(final Long projectId, final String email) {
        return (Invite) sessionFactory.getCurrentSession().createQuery("from Invite where email = :email and project.projectId = :id")
                .setParameter("email", email).setParameter("id", projectId).uniqueResult();
    }

}
