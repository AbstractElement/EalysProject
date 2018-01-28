
package com.sam_solutions.ealys.dao.impl;

import com.sam_solutions.ealys.dao.RiskDAO;
import com.sam_solutions.ealys.entity.Risk;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class for working with project risks table.
 */
@Repository
public class RiskDAOImpl extends GenericDAOImpl<Risk, Long> implements RiskDAO{
    /**
     * Return object who create hibernate-seesion
     */
    private final SessionFactory sessionFactory;

    /**
     * Amount risks on page (Property value: util.properties)
     */
    @Value("${risks.on.page}")
    private int amountRisksOnPage;

    @Autowired
    public RiskDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @see RiskDAO#getLimitRisks(Long, int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Risk> getLimitRisks(final Long projectId, final int start) {
        return (List<Risk>) sessionFactory.getCurrentSession()
                .createQuery("select r from Risk r where r.project.projectId = :id order by r.riskId desc")
                .setParameter("id", projectId)
                .setFirstResult(start).setMaxResults(amountRisksOnPage).list();
    }

    /**
     * @see RiskDAO#getAmountRisks(Long)
     */
    @Override
    public long getAmountRisks(final Long projectId) {
        return (long) sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Risk r where r.project.projectId = :id")
                .setParameter("id", projectId).uniqueResult();
    }
}
