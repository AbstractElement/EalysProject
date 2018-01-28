
package com.sam_solutions.ealys.dao.impl;

import com.sam_solutions.ealys.dao.SkillDAO;
import com.sam_solutions.ealys.entity.Skill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Class for working with user skills table.
 */
@Repository
public class SkillDAOImpl extends GenericDAOImpl<Skill, Long> implements SkillDAO {
    /**
     * Return object who create hibernate-session
     */
    private final SessionFactory sessionFactory;

    @Autowired
    public SkillDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Retrieves all skills.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Skill> getAllSkills(){
        return sessionFactory.getCurrentSession().createQuery("from Skill ").list();
    }

    /**
     * Retrieves skill by skill name.
     * @param name - skill name.
     */
    @Override
    public Skill getSkillByName(final String name) {
        return (Skill) sessionFactory.getCurrentSession().createQuery("from Skill where name = :name")
                .setParameter("name", name).uniqueResult();
    }
}
