
package com.sam_solutions.ealys.dao.impl;

import com.sam_solutions.ealys.dao.GenericDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Generic class with base CRUD operation.
 * @param <T> - Object
 * @param <PK> - Primary key of object
 */
@Repository
public class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {

    /**
     * Return object who create hibernate session
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @see GenericDAO#create(Object)
     */
    @Override
    public T create(final T object) {
        sessionFactory.getCurrentSession().persist(object);
        return object;
    }

    /**
     * @see GenericDAO#read(Class, Serializable)
     */
    @Override
    public T read(final Class<T> tClass, final PK id) {
        return sessionFactory.getCurrentSession().find(tClass, id);
    }

    /**
     * @see GenericDAO#update(Object)
     */
    @Override
    public T update(final T object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
        return object;
    }

    /**
     * @see GenericDAO#delete(Object)
     */
    @Override
    public void delete(final T object) {
        sessionFactory.getCurrentSession().delete(object);
    }
}
