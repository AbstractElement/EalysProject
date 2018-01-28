
package com.sam_solutions.ealys.dao.impl;

import com.sam_solutions.ealys.dao.ProjectDAO;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.RoleOnProject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class for working with user project table in DB.
 */
@Repository
public class ProjectDAOImpl extends GenericDAOImpl<Project, Long> implements ProjectDAO{
    /**
     * Return object who create hibernate session
     */
    private final SessionFactory sessionFactory;

    /**
     * Amount projects on page (Property value: util.prpoerties)
     */
    @Value("${projects.on.page}")
    private int amountProjectsOnPage;

    @Autowired
    public ProjectDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @see ProjectDAO#findLimitByUserId(Long, int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Project> findLimitByUserId(final Long userId, final int start) {
        return (List<Project>) sessionFactory.getCurrentSession().createQuery("select p from Project p join p.users pu where pu.user.userId = :id order by p.date desc ")
                .setParameter("id", userId)
                .setFirstResult(start).setMaxResults(amountProjectsOnPage).list();
    }

    /**
     * @see ProjectDAO#getAmountProjects(Long)
     */
    @Override
    public long getAmountProjects(final Long userId) {
        return (long) sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Project p join p.users pu where pu.user.userId = :id")
                .setParameter("id", userId).uniqueResult();
    }

    /**
     * @see ProjectDAO#findUserProject(Long, Long)
     */
    @Override
    public RoleOnProject findUserProject(final Long projectId, final Long userId) {
        return (RoleOnProject) sessionFactory.getCurrentSession()
                .createQuery("select pu from Project p join p.users pu where pu.user.userId = :uid and p.projectId = :pid")
                .setParameter("uid", userId).setParameter("pid", projectId).uniqueResult();
    }

}
