
package com.sam_solutions.ealys.dao.impl;

import com.sam_solutions.ealys.dao.UserDAO;
import com.sam_solutions.ealys.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Class for wotking with users table.
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO{
    /**
     * Return object who create hibernate-session
     */
    private final SessionFactory sessionFactory;

    /**
     * Amoung users on page (Property value: util properties)
     */
    @Value("${users.on.page}")
    private int amountUsersOnPage;

    @Autowired
    public UserDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @see UserDAO#getUsersFromInterval(int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersFromInterval(final int start) {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("from User")
                .setFirstResult(start).setMaxResults(amountUsersOnPage).list();
    }

    /**
     * Retrieve user by name.
     * @param name - user name.
     */
    @Override
    public User getUserByName(final String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :name");
        query.setParameter("name", name);
        return (User) query.uniqueResult();
    }

    /**
     * Retrieve user by email.
     * @param email - user email.
     */
    @Override
    public User getUserByEmail(final String email){
        Query query = sessionFactory.getCurrentSession().createQuery("from User where email = :email");
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

    /**
     * Retrieves users with selected skill.
     * @param skillName - skill name.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findUsersWithSkill(final String skillName, final int start) {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("select u from User u join u.skills us where us.name = :skillName")
                .setParameter("skillName", skillName)
                .setFirstResult(start).setMaxResults(amountUsersOnPage).list();

    }

    /**
     * @see UserDAO#getAmountUsersWithSkill(String)
     */
    @Override
    public long getAmountUsersWithSkill(final String skill){
        return (long) sessionFactory.getCurrentSession().createQuery("select count(*) from User u join u.skills us where us.name = :skillName")
                .setParameter("skillName", skill).uniqueResult();
    }

    /**
     * @see UserDAO#getAmountUsers()
     */
    @Override
    public long getAmountUsers() {
        return (long) sessionFactory.getCurrentSession().createQuery("select count(*) from User").uniqueResult();
    }
}
