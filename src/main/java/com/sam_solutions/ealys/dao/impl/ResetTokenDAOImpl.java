
package com.sam_solutions.ealys.dao.impl;

import com.sam_solutions.ealys.dao.ResetTokenDAO;
import com.sam_solutions.ealys.entity.ResetToken;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class for working with table of reset password tokens.
 */
@Repository
public class ResetTokenDAOImpl extends GenericDAOImpl<ResetToken, Long> implements ResetTokenDAO {

    /**
     * Return object who create hibernate session
     */
    private final SessionFactory sessionFactory;

    @Autowired
    public ResetTokenDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @see ResetTokenDAO#getByToken(String)
     */
    @Override
    public ResetToken getByToken(final String token) {
        return (ResetToken) sessionFactory.getCurrentSession().createQuery("from ResetToken where token = :token")
                .setParameter("token", token).uniqueResult();
    }
}
