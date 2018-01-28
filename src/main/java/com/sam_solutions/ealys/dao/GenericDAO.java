
package com.sam_solutions.ealys.dao;

import java.io.Serializable;

/**
 * Generic interface with base CRUD operation.
 * @param <T> - Object
 * @param <PK> - Primary key of object
 */
public interface GenericDAO<T, PK extends Serializable> {

    /**
     * Add new row in DB
     * @param t - entity object
     */
    T create(T t);

    /**
     * Retrieves object from DB by primary key
     * @param tClass - object type.
     * @param id - object id.
     */
    T read(Class<T> tClass, PK id);

    /**
     * Updating row in DB
     * @param t - object with new data
     */
    T update(T t);

    /**
     * Deleting row from DB
     * @param t - object
     */
    void delete(T t);
}
